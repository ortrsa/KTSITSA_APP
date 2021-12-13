package com.example.ktsitsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddIngredient extends AppCompatActivity {


    private String ing_Name, ing_category;
    private FirebaseDatabase db;
    private DatabaseReference dbr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);
        Button add = findViewById(R.id.BtnAdd1);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ing_Name = ((EditText) findViewById(R.id.TxtingTitle)).getText().toString();
//                ing_category = ((EditText) findViewById(R.id.TxtingTitle2)).getText().toString();
//                if(!ing_Name.isEmpty() && !ing_category.isEmpty()){
                    String toadd = "חומוס,לחם,מלח";
                    String[] toaddsplit =toadd.split(",");
                    for (int i = 0; i < toaddsplit.length ; i++) {


                        // add to firebase
                        db = FirebaseDatabase.getInstance();
                        dbr = db.getReference("Ingredients");
                        DatabaseReference pushrecipes = dbr.push();
                        Ingredients ing = new Ingredients(pushrecipes.getKey(), toaddsplit[i], "בשר");
                        pushrecipes.setValue(ing).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                ((EditText) findViewById(R.id.TxtingTitle)).setText("");
                                ((EditText) findViewById(R.id.TxtingTitle2)).setText("");
                            }
                        });
                    }




//                }


            }
        });



    }
}