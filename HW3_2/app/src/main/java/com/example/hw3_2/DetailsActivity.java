package com.example.hw3_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.os.Bundle;
import android.content.Context;
import android.widget.RelativeLayout;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DESC said ...";

    private static final int RESULT_ID = 3;

    private DetailsAdapter adapter;
    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private RelativeLayout relativeLayout;
    private int position = RecyclerViewActivity.getListPosition();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler);
        context = getApplicationContext();
        relativeLayout = findViewById(R.id.relative_layout);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        adapter = new DetailsAdapter(context, RecyclerViewActivity.movies[position]);
        recyclerView.setAdapter(adapter);
    }
}
