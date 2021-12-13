package com.example.ktsitsa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.inappmessaging.model.Button;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class imagRV_adapter extends RecyclerView.Adapter<imagRV_adapter.ViewHolder> {

    private ArrayList<Recipes> respList;
    private Context context;
    private StorageReference storageReference;
    private Boolean IsAdmin;

    public imagRV_adapter(ArrayList<Recipes> respList, AppCompatActivity activity, Boolean IsAdmin){
        this.respList = respList;
        this.context = activity;
        this.IsAdmin = IsAdmin;
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
        //Get the recipe from respList
        final Recipes r = respList.get(position);
        holder.respName.setText(r.getRecipeName());
        holder.respdisc.setText(r.getRecipeDescription());

        // Get the recipe image from firebase
        storageReference = FirebaseStorage.getInstance().getReference(r.getRecipeImage());
        try {
            File localFile = File.createTempFile("temp" + r.getKey() ,"jpeg");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(@NonNull FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    holder.SIV.setImageBitmap(bitmap);
                    holder.cardview.setVisibility(View.VISIBLE);
                    localFile.deleteOnExit();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Fail to load image!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set on Click, when clicking on recipe it will open in new layout!
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipeDynamic.class);

                intent.putExtra("recipeName",r.getRecipeName());
                intent.putExtra("recipeIngredients",r.getRecipeIngredients());
                intent.putExtra("recipeDescription",r.getRecipeDescription());
                intent.putExtra("recipeImage",r.getRecipeImage());
                intent.putExtra("recipeKey",r.getKey());
                intent.putExtra("isAdmin",IsAdmin);

                context.startActivity(intent);

            }
        });

        //If this is the user that add this recipe he can delete it!
        String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(r.getUid().equals(Uid)) {
            holder.Delete.setVisibility(View.VISIBLE);
            holder.Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseReference database = FirebaseDatabase.getInstance().getReference("recipes").child(r.getKey());
                    database.removeValue();
                    respList.remove(r);
                    Toast.makeText(context, "Recipe Delete!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return respList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ShapeableImageView SIV;
        private TextView respName;
        private TextView respdisc;
        private CircularRevealCardView cardview;
        private TextView Delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            SIV = itemView.findViewById(R.id.imageView3);
            respName = itemView.findViewById(R.id.respName);
            respdisc = itemView.findViewById(R.id.respdisp1);
            cardview = itemView.findViewById(R.id.cardViewID);
            Delete = itemView.findViewById(R.id.Deletbutton);
        }


    }
}
