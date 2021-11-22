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

        ArrayList<Integer> a = new ArrayList<>();
        a.add(R.drawable.whatsapp_image_2021_11_15_at_8_42_12_pm);
        a.add(R.drawable.whatsapp_image_2021_11_15_at_8_24_40_pm);
        a.add(R.drawable.dan_gold_4_jhdo54byg_unsplash);
        a.add(R.drawable.whatsapp_image_2021_11_15_at_8_42_12_pm);
        a.add(R.drawable.whatsapp_image_2021_11_15_at_8_24_40_pm);
        a.add(R.drawable.dan_gold_4_jhdo54byg_unsplash);
        a.add(R.drawable.whatsapp_image_2021_11_15_at_8_42_12_pm);
        a.add(R.drawable.whatsapp_image_2021_11_15_at_8_24_40_pm);
        a.add(R.drawable.dan_gold_4_jhdo54byg_unsplash);

        imagRV_adapter ada = new imagRV_adapter();
        ada.setImg(a);
        RecRV.setAdapter(ada);
        RecRV.setLayoutManager(new LinearLayoutManager(this));
    }
}