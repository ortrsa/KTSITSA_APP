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

    public void HomeBtnClick(View view) {
        Intent intent = new Intent(RecommendedActivity.this, MainActivity.class);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);


        database = FirebaseDatabase.getInstance().getReference("recipes");
        RecRV = findViewById(R.id.recRV);
        RecRV.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Recipes> RecList  = new ArrayList<>();
        imagRV_adapter ada = new imagRV_adapter(RecList,RecommendedActivity.this);
        RecRV.setAdapter(ada);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Recipes r = dataSnapshot.getValue(Recipes.class);
                    RecList.add(r);
                }

                ada.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//            a.add(new Recipes("avf","asddsdss","asffgnsdd ","dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd\ndasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" +
//                    "dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" +
//                    "dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" +
//                    "dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" +
//                    "dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" +
//                    "dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" ,R.drawable.whatsapp_image_2021_11_15_at_8_42_12_pm));
//            a.add(new Recipes("aerff","aasddss","dassd","afsnfgndsd",R.drawable.whatsapp_image_2021_11_15_at_8_24_40_pm));
//            a.add(new Recipes("afsd","asdaadss","asdasd","assfngfgfdd",R.drawable.dan_gold_4_jhdo54byg_unsplash));
//            a.add(new Recipes("sdff","asddsss","asdasd","asnfgnsdfd",R.drawable.charles_deluvio_d_vdqmtfaau_unsplash));







    }
}