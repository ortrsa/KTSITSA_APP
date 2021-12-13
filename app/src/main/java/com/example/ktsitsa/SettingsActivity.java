package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    private Boolean IsAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        IsAdmin = getIntent().getExtras().getBoolean("isAdmin");
        Button adding = findViewById(R.id.Btnadding1);

        if(IsAdmin){
          adding.setVisibility(View.VISIBLE);
        }
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, AddIngredient.class);
                intent.putExtra("isAdmin",IsAdmin);
                startActivity(intent);
            }
        });





    }


    public void HomeBtnClick(View view) {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);

    }


    public void LogOut(View view) {
         FirebaseAuth.getInstance().signOut();
         startActivity(new Intent(this, LogIn.class));
         finish();

    }
}