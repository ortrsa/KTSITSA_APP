package com.example.ktsitsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecommendedActivity extends AppCompatActivity {

    private RecyclerView RecRV ;
    private DatabaseReference database;
    private Boolean IsAdmin;

    public void HomeBtnClick(View view) {
        Intent intent = new Intent(RecommendedActivity.this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);
        IsAdmin = getIntent().getExtras().getBoolean("isAdmin");

        database = FirebaseDatabase.getInstance().getReference("recipes");
        RecRV = findViewById(R.id.recRV);
        RecRV.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Recipes> RecList  = new ArrayList<>();

        imagRV_adapter ada = new imagRV_adapter(RecList,RecommendedActivity.this, IsAdmin);
        RecRV.setAdapter(ada);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Recipes r = dataSnapshot.getValue(Recipes.class);
                    if (r.isApproved()|| IsAdmin) {
                        RecList.add(r);

                    }
                }

                ada.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}