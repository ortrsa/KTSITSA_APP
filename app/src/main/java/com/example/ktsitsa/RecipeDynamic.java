package com.example.ktsitsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.File;
import java.io.IOException;

public class RecipeDynamic extends AppCompatActivity {


    private TextView mRecipeName, mRecipeIngredients, mRecipeMethod, mRecipe;
    private CheckBox checkBox;
    private LinearLayout LL;
    private ShapeableImageView mRecipeImage;
    private DatabaseReference database;
    private StorageReference storageReference;
    private ProgressDialog progressDialog;
    private Boolean IsApproved;
    private FirebaseAuth mAuth;
    private Boolean IsAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_dynamic);


        progressDialog = new ProgressDialog(RecipeDynamic.this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();

        mRecipeName = (TextView) findViewById(R.id.text_recipe);
        mRecipeIngredients = (TextView) findViewById(R.id.Text_Ingredients);
        mRecipeMethod = (TextView) findViewById(R.id.Method);
        mRecipe = (TextView) findViewById(R.id.recipe);
        mRecipeImage = (ShapeableImageView) findViewById(R.id.Respimage);
        LL = (LinearLayout) findViewById(R.id.LLRecipeDynamic);
        checkBox = (CheckBox) findViewById(R.id.approveCB);
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();


        Intent intent = getIntent();
        String Title = intent.getExtras().getString("recipeName");
        String Ingredients = intent.getExtras().getString("recipeIngredients");
        String Description = intent.getExtras().getString("recipeDescription");
        String image = intent.getExtras().getString("recipeImage");
        String Key = intent.getExtras().getString("recipeKey");
        IsAdmin = intent.getExtras().getBoolean("isAdmin");

        database = FirebaseDatabase.getInstance().getReference("recipes").child(Key).child("approved");

        database.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                IsApproved = Boolean.parseBoolean(task.getResult().getValue().toString());
                checkBox.setChecked(IsApproved);
            }
        });


        mRecipeName.setText(Title);
        mRecipeIngredients.setText(Ingredients);
        mRecipeMethod.setText("רכיבים");
        mRecipe.setText(Description);
        storageReference = FirebaseStorage.getInstance().getReference(image);
        try {
            File localFile = File.createTempFile("tempz", "jpeg");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(@NonNull FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    mRecipeImage.setImageBitmap(bitmap);
                    localFile.delete();
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    LL.setVisibility(View.VISIBLE);
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


        // if admin is log in show approved check box


        if (IsAdmin) {
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    IsApproved = false;
                    if (isChecked) {
                        database.setValue(true);
                    } else {
                        database.setValue(false);
                    }
                }
            });
        }

    }
}