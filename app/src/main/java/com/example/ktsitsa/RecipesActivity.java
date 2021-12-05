package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecipesActivity extends AppCompatActivity {

    private Button b1,b2;
    private Boolean IsAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);


        IsAdmin = getIntent().getExtras().getBoolean("isAdmin");
        initbutton();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipesActivity.this, user_approved_rec.class);
                intent.putExtra("isAdmin",IsAdmin);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipesActivity.this, user_not_approved_rec.class);
                intent.putExtra("isAdmin",IsAdmin);
                startActivity(intent);
            }
        });


    }

    private void initbutton() {
        b1 = findViewById(R.id.approvedrec);
        b2 = findViewById(R.id.notapprovedrec);

    }

}