package com.example.hw3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class    MainActivity extends AppCompatActivity {

    private static final String TAG = "TAZ said ...";

    private static final int RESULT_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    protected void onClick(View button) {
        if(button.getId() == R.id.button1) {
            Intent movieList = new Intent(this, RecyclerViewActivity.class);
            startActivityForResult(movieList, RESULT_ID);
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Would you like toast!?!";
            if(button.getId() == R.id.button3){
                text = "Wouldn't toast be great?!";
            }
            else if (button.getId() == R.id.button4){
                text = "More toast?";
            }
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
    }
}
