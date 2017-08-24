package com.example.alpin.makeup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alpin.makeup.model.Eyebrow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alpin on 24/08/17.
 */

public class AdapterEyebrow extends RecyclerView.Adapter<AdapterEyebrow.MyViewHolder> {

    List<Eyebrow> dataset;
    Context context;

    public AdapterEyebrow(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }
    public AdapterEyebrow(Context context,List<Eyebrow> dataset){
        this.context = context;
        this.dataset = dataset;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEye;
        TextView title,name,price;
        public MyViewHolder(View itemView) {
            super(itemView);
            imgEye = itemView.findViewById(R.id.img);
            title =  itemView.findViewById(R.id.tv_title);
            name = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.tv_price);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actiivity_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Eyebrow eyebrow = dataset.get(position);
        holder.name.setText(eyebrow.getName());
        holder.title.setText(eyebrow.getTitle());
        holder.price.setText(eyebrow.getPrice());
        Glide.with(context).load(eyebrow.getImage()).into(holder.imgEye);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public void addAll(List<Eyebrow> list){
        dataset.addAll(list);
        notifyDataSetChanged();
    }
    public void clearData(){
        dataset.clear();
        notifyDataSetChanged();
    }
    public void updateData(List<Eyebrow> list) {
        if (dataset.size() != list.size() || !dataset.containsAll(list)) {
            dataset.addAll(list);
            notifyDataSetChanged();
        }
    }
}
