package com.example.hw2;

import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "ILRU SAYS ...";

    public static final String RESULT = "my.response";

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        String message = "";

        message = getIntent().getStringExtra(MainActivity.MESSAGE_ID);

        setContentView(R.layout.activity_second);

        TextView label = findViewById(R.id.intent_message);
        label.setText(message);

        Log.i(TAG, "created");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "started");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "resumed");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "paused");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "stopped");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "destroyed");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

}
