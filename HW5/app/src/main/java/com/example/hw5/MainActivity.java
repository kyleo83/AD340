package com.example.hw5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "TAZ said ...";

    private String numberOfCabins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner)findViewById(R.id.num_cabins);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.num_of_cabins,
                        android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos,long id) {
        numberOfCabins = (parent.getItemAtPosition(pos)).toString();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        numberOfCabins = "0";
    }


    public void onMyClick(View button) {

        Log.i(TAG, this.getLifecycle().getCurrentState().toString());

        EditText editText = findViewById(R.id.text_age);
        TextView outPut = findViewById(R.id.yacht_price);
        int numCabins = Integer.parseInt(numberOfCabins);

        String age = editText.getText().toString();
        if(!age.isEmpty()) {
            Yacht newBoat = new Yacht("Minnow", numCabins, 2);
            double price = newBoat.determinePrice(Integer.parseInt(age));
            String strPrice = String.format("$%.2f", price);
            outPut.setText(strPrice);
        } else {
            Context context = getApplicationContext();
            String text = "Input is required!";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        }


    }
}
