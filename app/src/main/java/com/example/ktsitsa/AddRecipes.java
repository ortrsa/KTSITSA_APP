package com.example.ktsitsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRecipes extends AppCompatActivity {



    String recipeName, recipeIngredients, recipeDescription;
    FirebaseDatabase db;
    DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void HomeBtnClick(View view) {
        Intent intent = new Intent(AddRecipes.this, MainActivity.class);
        startActivity(intent);

    }

    public void add(View view) {

        recipeName = ((EditText) findViewById(R.id.TxtTitle)).getText().toString();
        recipeIngredients =  ((EditText)findViewById(R.id.TxtIngredients)).getText().toString();
        recipeDescription = ((EditText) findViewById(R.id.txtInstructions)).getText().toString();

        if(!recipeName.isEmpty() && !recipeIngredients.isEmpty() && !recipeDescription.isEmpty()){
            Recipes r = new Recipes(recipeName,"method",recipeIngredients,recipeDescription,R.drawable.dan_gold_4_jhdo54byg_unsplash);

            db = FirebaseDatabase.getInstance();
            dbr = db.getReference("recipes");
            dbr.child(recipeName).setValue(r).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
//                    binding.PersonName.setText("");
//                    binding.LastNameId.setText("");
//                    binding.SexId.setText("");
                    Toast.makeText(AddRecipes.this, "נשמר בהצלחה", Toast.LENGTH_SHORT).show();
                }
            });


        }

    }
}