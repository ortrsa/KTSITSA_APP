package com.example.ktsitsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class RecipeDynamic extends AppCompatActivity {


    private TextView mRecipeName;
    private TextView mRecipeIngredients;
    private TextView mRecipeMethod;
    private TextView mRecipe;
    private ShapeableImageView mRecipeImage;
    StorageReference storageReference;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_dynamic);


        progressDialog = new ProgressDialog(RecipeDynamic.this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();

        mRecipeName = (TextView)findViewById(R.id.text_recipe);
        mRecipeIngredients = (TextView)findViewById(R.id.Text_Ingredients);
        mRecipeMethod = (TextView)findViewById(R.id.Method);
        mRecipe = (TextView)findViewById(R.id.recipe);
        mRecipeImage = (ShapeableImageView)findViewById(R.id.Respimage);


        Intent intent = getIntent();
        String Title = intent.getExtras().getString("recipeName");
        String Ingredients = intent.getExtras().getString("recipeIngredients");
        String Description = intent.getExtras().getString("recipeDescription");
        String Method = intent.getExtras().getString("method");
        String image = intent.getExtras().getString("recipeImage");

        mRecipeName.setText(Title);
        mRecipeIngredients.setText(Ingredients);
        mRecipeMethod.setText(Method);
        mRecipe.setText(Description);
        storageReference = FirebaseStorage.getInstance().getReference(image);
        try {
            File localFile = File.createTempFile("tempz" ,"jpeg");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(@NonNull FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    mRecipeImage.setImageBitmap(bitmap);
                    localFile.delete();
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();


                    // add no image photo
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}