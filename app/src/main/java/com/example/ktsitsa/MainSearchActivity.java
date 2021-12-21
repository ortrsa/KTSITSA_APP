package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainSearchActivity extends AppCompatActivity {

    private Boolean IsAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);





    }


    public void search_ing_on_click(View view) {
            Intent intent = new Intent(MainSearchActivity.this, Search_by_Ingredient.class);
            intent.putExtra("isAdmin",IsAdmin);
            startActivity(intent);
    }

    public void search_by_name_on_click(View view) {
    }
}