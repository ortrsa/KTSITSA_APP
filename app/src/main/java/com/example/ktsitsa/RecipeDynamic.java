package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

public class RecipeDynamic extends AppCompatActivity {


    private TextView mRecipeName;
    private TextView mRecipeIngredients;
    private TextView mRecipeMethod;
    private TextView mRecipe;
    private ShapeableImageView mRecipeImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_dynamic);

        mRecipeName = (TextView)findViewById(R.id.text_recipe);
        mRecipeIngredients = (TextView)findViewById(R.id.Text_Ingredients);
        mRecipeMethod = (TextView)findViewById(R.id.Method);
        mRecipe = (TextView)findViewById(R.id.recipe);
        mRecipeImage = (ShapeableImageView)findViewById(R.id.Respimage);


        Intent intent = getIntent();
        String Title = intent.getExtras().getString("recipeName");
        String Ingredients = intent.getExtras().getString("recipeIngredients");
        String Description = intent.getExtras().getString("recipeDescription");
        String Method = intent.getExtras().getString("method");
        Integer image = intent.getExtras().getInt("recipeImage");

        mRecipeName.setText(Title);
        mRecipeIngredients.setText(Ingredients);
        mRecipeMethod.setText(Method);
        mRecipe.setText(Description);
        mRecipeImage.setImageResource(image);




    }
}