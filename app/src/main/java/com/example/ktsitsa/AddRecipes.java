package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRecipes extends AppCompatActivity {

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

        EditText TxtTitle= (EditText)findViewById(R.id.TxtTitle);
        String Title =TxtTitle.getText().toString();

        EditText TxtIngredients= (EditText)findViewById(R.id.TxtIngredients);
        String Ingredients =TxtTitle.getText().toString();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Title");
        myRef.setValue(Title);

        DatabaseReference myRefX = database.getReference("Ingredients");
        myRef.setValue(Ingredients);





    }
}