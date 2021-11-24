package com.example.ktsitsa;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddRecipes extends AppCompatActivity {



    String recipeName, recipeIngredients, recipeDescription;
    FirebaseDatabase db;
    DatabaseReference dbr;
    ActivityResultLauncher<String> mGetImage;
    StorageReference STR;
    ProgressDialog progressDialog;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        
        mGetImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                ((ImageView)findViewById(R.id.imageView6)).setImageURI(result);
                imageUri = result;

            }
        });
    }

    public void HomeBtnClick(View view) {
        Intent intent = new Intent(AddRecipes.this, MainActivity.class);
        startActivity(intent);

    }

    public void add(View view) {

        recipeName = ((EditText) findViewById(R.id.TxtTitle)).getText().toString();
        recipeIngredients =  ((EditText)findViewById(R.id.TxtIngredients)).getText().toString();
        recipeDescription = ((EditText) findViewById(R.id.txtInstructions)).getText().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);
        if(!recipeName.isEmpty() && !recipeIngredients.isEmpty() && !recipeDescription.isEmpty()){

            if(imageUri != null) {
                upimage(fileName);
            }

            Recipes r = new Recipes(recipeName,"method",recipeIngredients,recipeDescription,"images/" + fileName + ".jpeg");

            db = FirebaseDatabase.getInstance();
            dbr = db.getReference("recipes");
            dbr.child(recipeName).setValue(r).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    ((EditText)findViewById(R.id.TxtTitle)).setText("");
                    ((EditText)findViewById(R.id.TxtIngredients)).setText("");
                    ((EditText)findViewById(R.id.txtInstructions)).setText("");
                    Toast.makeText(AddRecipes.this, "נשמר בהצלחה", Toast.LENGTH_SHORT).show();
                }
            });


        }

    }

    private void upimage(String fileName) {

        progressDialog = new ProgressDialog(AddRecipes.this);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();



        STR = FirebaseStorage.getInstance().getReference("images/" + fileName + ".jpeg");
        STR.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        ((ImageView)findViewById(R.id.imageView6)).setImageURI(null);
                        Toast.makeText(AddRecipes.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(AddRecipes.this,"Failed to Upload",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addimage(View view) {

        mGetImage.launch("image/*");

    }




}