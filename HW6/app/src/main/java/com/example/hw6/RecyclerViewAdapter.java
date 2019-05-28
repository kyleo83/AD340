package com.example.hw6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private TrafficCamera[] cameras;

    public RecyclerViewAdapter(ArrayList<TrafficCamera> cameras) {
        this.cameras = cameras.toArray(new TrafficCamera[0]);
    }


    @Override
    public int getItemCount() {

            return cameras.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CardView layout;

        public ViewHolder(CardView v){
            super(v);
            layout = v;
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(cardView);
    }

    @Override

    public void onBindViewHolder(ViewHolder holder, final int position) {

        CardView cardView = holder.layout;
        TextView textView = (TextView) cardView.findViewById(R.id.camera_name);
        ImageView imageView = (ImageView)cardView.findViewById(R.id.camera_image);

        Context context = cardView.getContext();

        TrafficCamera camera = cameras[position];
        textView.setText(cameras[position].getCameraLocation());
        Picasso.with(context).load(cameras[position].getCameraImage()).into(imageView);
    }
}
