package com.example.hw6;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    private static final String TAG = "MAIN_ACTIVITY";
    public static final String DESCRIPTION_ID = "my.description";
    public static final String LATITUDE = "my.latitude";
    public static final String LONGITUDE = "my.longitude";

    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private RelativeLayout relativeLayout;
    private TextView results;
    private TrafficCamera[] mainCameras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        results = findViewById(R.id.results_label);

        ConnectivityManager manager =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        results.setText(getResources().getString(R.string.waiting));

        if (info != null && info.isConnected()) {
            Bundle bundle = new Bundle();
            bundle.putString("queryString", "");
            getSupportLoaderManager().restartLoader(0, bundle, this);
        } else {
            results.setText(getResources().getString(R.string.not_connected));
        }

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";
        if (bundle !=  null) {
            queryString = bundle.getString("queryString");
        }
        return new CameraLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {

        String description = "";
        String imageUri = "";
        String dotType = "";
        results.setText("");

        final ArrayList<TrafficCamera> cameraArray = new ArrayList<>();
        try {
            JSONObject rootObject = new JSONObject(s);
            JSONArray features =  rootObject.getJSONArray("Features");
            for(int i = 0; i < features.length(); i++) {
                JSONObject myResult = features.getJSONObject(i);
                JSONArray camera = myResult.getJSONArray("Cameras");
                JSONArray jsonCoordinates = myResult.getJSONArray("PointCoordinate");
                double myLat = jsonCoordinates.getDouble(0);
                double myLong = jsonCoordinates.getDouble(1);
                for(int j = 0; j < camera.length(); j++) {
                    JSONObject myCamera = camera.getJSONObject(j);
                    description = myCamera.getString("Description");
                    imageUri = myCamera.getString("ImageUrl");
                    dotType = myCamera.getString("Type");
                    if (dotType.equals("sdot")) {
                        imageUri = "http://www.seattle.gov/trafficcams/images/" + imageUri;
                    } else {
                        imageUri = "http://images.wsdot.wa.gov/nw/" + imageUri;
                    }
                    cameraArray.add(new TrafficCamera.Builder()
                            .cameraLocation(description)
                            .cameraImage(imageUri)
                            .cameraLatitude(myLat)
                            .cameraLongitude(myLong)
                            .build());
                }
            }


        } catch(Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }

        adapter = new RecyclerViewAdapter(cameraArray);
        recyclerView.setAdapter(adapter);

        mainCameras = cameraArray.toArray(new TrafficCamera[0]);

        adapter.setListener(new RecyclerViewAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Log.i(TAG, "Clicked");
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra(DESCRIPTION_ID, mainCameras[position].getCameraLocation());
                intent.putExtra(LATITUDE, mainCameras[position].getCameraLatitude());
                intent.putExtra(LONGITUDE, mainCameras[position].getCameraLongitude());
                startActivity(intent);
            }
        });
    }

        public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
