package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button b1,b2,b3,b4;
    private Boolean IsAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IsAdmin = getIntent().getExtras().getBoolean("isAdmin");
        initbutton();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Search_Options.class);
                    intent.putExtra("isAdmin",IsAdmin);
                    startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultsSearchByIngActivity.class);
                intent.putExtra("isAdmin",IsAdmin);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Ingredients_CheckBox.class);
                intent.putExtra("isAdmin",IsAdmin);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                intent.putExtra("isAdmin",IsAdmin);
                startActivity(intent);
            }
        });
    }

    private void initbutton() {
        b1 = findViewById(R.id.buttonrecipes);
        b2 = findViewById(R.id.buttonrec);
        b3 = findViewById(R.id.buttonADD);
        b4 = findViewById(R.id.buttonsettings);
    }
}