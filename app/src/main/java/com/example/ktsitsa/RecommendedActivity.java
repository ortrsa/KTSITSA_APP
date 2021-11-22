package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class RecommendedActivity extends AppCompatActivity {

    private RecyclerView RecRV ;

    public void HomeBtnClick(View view) {
        Intent intent = new Intent(RecommendedActivity.this, MainActivity.class);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);

        RecRV = findViewById(R.id.recRV);

        Recipes[] a = new Recipes[]{
            new Recipes("avf","asddsdss","asffgnsdd ","dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd\ndasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" +
                    "dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasddasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" +
                    "dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" +
                    "dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" +
                    "dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" +
                    "dasd\nasd\nasdasd\nasdasd\nsadasd\nasd\nasd\nasdasdas\ndasdasd\nasdasda\nsadasdasd\nsadasdasd\ndasdasdasasddnasd\ndsaasd" ,R.drawable.whatsapp_image_2021_11_15_at_8_42_12_pm),
            new Recipes("aerff","aasddss","dassd","afsnfgndsd",R.drawable.whatsapp_image_2021_11_15_at_8_24_40_pm),
            new Recipes("afsd","asdaadss","asdasd","assfngfgfdd",R.drawable.dan_gold_4_jhdo54byg_unsplash),
            new Recipes("sdff","asddsss","asdasd","asnfgnsdfd",R.drawable.charles_deluvio_d_vdqmtfaau_unsplash)
        };



        imagRV_adapter ada = new imagRV_adapter(a,RecommendedActivity.this);
        RecRV.setAdapter(ada);
        RecRV.setLayoutManager(new LinearLayoutManager(this));
    }
}