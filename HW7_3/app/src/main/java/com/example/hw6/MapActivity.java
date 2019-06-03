package com.example.hw6;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends AppCompatActivity
    implements OnMapReadyCallback {

    private static final String TAG = "MAP_ACTIVITY";

    private FusedLocationProviderClient gLocationClient;
    private boolean gLocationPermissionGranted;
    private GoogleMap gMap;
    private String description;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map );

        description = getIntent().getStringExtra(MainActivity.DESCRIPTION_ID);
        latitude = getIntent().getDoubleExtra(MainActivity.LATITUDE, 47.7);
        longitude = getIntent().getDoubleExtra(MainActivity.LONGITUDE, -122.3);
        Log.i(TAG, String.valueOf(latitude) + " " + String.valueOf(longitude));
        gLocationClient = LocationServices
                .getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this);
    }

    @SuppressLint("missingPermission")
    private void getLocation() {
        if (gLocationPermissionGranted) {
            Task location = gLocationClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener<Location>() {

                @Override
                public void onComplete(Task<Location> task) {
                    Location actualLocation = task.getResult();
                    if(actualLocation != null) {
                        String latLong = String.format("Lat: %f, Long: %f",
                                actualLocation.getLatitude(),
                                actualLocation.getLongitude());

                        gMap.setMyLocationEnabled(true);
                        gMap.getUiSettings().setMyLocationButtonEnabled(true);

                        LatLng here = new LatLng(actualLocation.getLatitude(),
                                actualLocation.getLongitude());
                        gMap.addMarker(new MarkerOptions().position(here).title("Current Location"));
                        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 11));

                        gMap.animateCamera(CameraUpdateFactory.zoomTo(11));

                    } else {
                        Log.e(TAG, "Location Not Found");
                    }
                }
            });
            location.addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            });

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        gLocationPermissionGranted = false;
        switch(requestCode) {
            case 1: {
                if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gLocationPermissionGranted = true;
                    getLocation();
                }
            }
        }
    }

    private void getLocationPermission() {
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            gLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.addMarker(new MarkerOptions()
            .position(new LatLng(latitude, longitude))
            .title(description));
        getLocationPermission();
        getLocation();
    }
}
