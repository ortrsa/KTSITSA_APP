package com.example.ktsitsa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class imagRV_adapter extends RecyclerView.Adapter<imagRV_adapter.ViewHolder> {

    Recipes[] respList;
    Context context;

    public imagRV_adapter(Recipes[] respList, RecommendedActivity activity){
        this.respList = respList;
        this.context = activity;

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
        final Recipes r = respList[position];
        holder.respName.setText(r.getRecipeName());
        holder.respdisc.setText(r.getRecipeDescription());
        holder.SIV.setImageResource(r.getRecipeImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, r.getRecipeName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, RecipeDynamic.class);

                intent.putExtra("recipeName",r.getRecipeName());
                intent.putExtra("recipeIngredients",r.getRecipeIngredients());
                intent.putExtra("method",r.getMethod());
                intent.putExtra("recipeDescription",r.getRecipeDescription());
                intent.putExtra("recipeImage",r.getRecipeImage());

                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return respList.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ShapeableImageView SIV;
        private TextView respName;
        private TextView respdisc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            SIV = itemView.findViewById(R.id.imageView3);
            respName = itemView.findViewById(R.id.respName);
            respdisc = itemView.findViewById(R.id.respdisp1);
        }


    }
}
