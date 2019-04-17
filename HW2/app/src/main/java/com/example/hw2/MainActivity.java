package com.example.hw2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MajorK SAYS ...";
    private static final int RESULT_ID = 1;

    public static final String MESSAGE_ID = "my.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    protected void onClick(View button) {

        EditText textBox = (EditText)findViewById(R.id.message_box);
        String message = textBox.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(MESSAGE_ID, message);
        startActivityForResult(intent, RESULT_ID);
    }

//    @Override
//    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.i(TAG, "ActivityResult Run ");
//        if (requestCode == RESULT_ID) {
//            if (resultCode == RESULT_OK) {
//                TextView label = (TextView)findViewById(R.id.message);
//                String message = label.getText().toString();
//                message += "\n\n" + data.getStringExtra(SecondActivity.RESULT);
//                label.setText(message);
//            }
//        }
//    }

}
