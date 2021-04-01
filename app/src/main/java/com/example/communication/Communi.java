package com.example.communication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Communi extends RecyclerView.Adapter<Communi.ViewHolder> {

    private List<model> mData;
    private LayoutInflater mInflater;


    // data is passed into the constructora.fm
    Communi(Context context, List<model> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(mData.get(position).getName());
        holder.phone.setText(mData.get(position).getPhone());
        holder.address.setText(mData.get(position).getAddress());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView name;
        TextView phone;
        TextView address;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            phone = itemView.findViewById(R.id.Phone);
            address = itemView.findViewById(R.id.Address);


        }


    }




}
