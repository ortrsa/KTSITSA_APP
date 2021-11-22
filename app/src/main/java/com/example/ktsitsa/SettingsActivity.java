package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void HomeBtnClick(View view) {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);

    }
    public void BtnLogIn(View view) {
        Intent intent = new Intent(SettingsActivity.this, LogIn.class);
        startActivity(intent);

    }

    public void LogOut(View view) {
         FirebaseAuth.getInstance().signOut();
         startActivity(new Intent(this, LogIn.class));
         finish();

    }
}