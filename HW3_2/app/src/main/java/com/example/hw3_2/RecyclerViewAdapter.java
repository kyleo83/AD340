package com.example.hw3_2;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    String[][] SubjectValues;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onClick(int position);
    }

    public RecyclerViewAdapter(Context context1,String[][] SubjectValues1){
        SubjectValues = SubjectValues1;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleView;
        public TextView yearView;

        public ViewHolder(View v){
            super(v);
            titleView = (TextView)v.findViewById(R.id.title);
            yearView = (TextView)v.findViewById(R.id.year);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_items,parent,false);
        viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        holder.titleView.setText(SubjectValues[position][0]);
        holder.yearView.setText(SubjectValues[position][1]);

        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return SubjectValues.length;
    }
}
