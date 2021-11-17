package com.example.ktsitsa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class imagRV_adapter extends RecyclerView.Adapter<imagRV_adapter.ViewHolder> {
    private ArrayList<Integer> img = new ArrayList<>();
    public imagRV_adapter(){

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommmended_list, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.SIV.setImageResource(img.get(position));
    }

    @Override
    public int getItemCount() {
        return img.size();
    }

    public void setImg(ArrayList<Integer> img) {
        this.img = img;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ShapeableImageView SIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SIV = itemView.findViewById(R.id.imageView3);
        }


    }
}
