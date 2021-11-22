package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class templet_start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.templet);
//        ofir 111111111
    }

    public void HomeBtnClick(View view) {
        Intent intent = new Intent(templet_start.this, MainActivity.class);
        startActivity(intent);

    }
}