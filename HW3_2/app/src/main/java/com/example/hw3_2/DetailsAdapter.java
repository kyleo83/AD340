package com.example.hw3_2;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    private static final String TAG = "DEAD said ...";

    private String[] SubjectValues;
    private Context context;
    private View view1;
    private DetailsAdapter.ViewHolder viewHolder1;

    public DetailsAdapter(Context context1,String[] SubjectValues1){
        SubjectValues = SubjectValues1;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleView;
        public ImageView imageView;
        public TextView directorView;
        public TextView descriptionView;

        public ViewHolder(View v){
            super(v);
            titleView = v.findViewById(R.id.title);
            imageView = v.findViewById(R.id.image);
//            directorView = v.findViewById(R.id.director);
//            descriptionView = v.findViewById(R.id.description);
        }
    }

    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        view1 = LayoutInflater.from(context).inflate(R.layout.activity_details,parent,false);
        viewHolder1 = new DetailsAdapter.ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(DetailsAdapter.ViewHolder holder, final int position){
        if(position == 0) {
            holder.titleView.setText("Title: " + SubjectValues[position]);
        }
        else if(position == 1) {
            holder.titleView.setText("Year: " + SubjectValues[position]);
        }
        else if(position == 2) {
            holder.titleView.setText("Director: " + SubjectValues[position]);
        }
        else if(position == 3) {
            holder.imageView.setImageURI(Uri.parse(SubjectValues[position]));
            Log.i(TAG, SubjectValues[position]);
        }
        else {
            holder.titleView.setText("Description:\n" + SubjectValues[position]);
        }
    }

    @Override
    public int getItemCount(){
        return SubjectValues.length;
    }
}

