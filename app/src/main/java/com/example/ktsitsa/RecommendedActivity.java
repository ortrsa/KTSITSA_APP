package com.example.ktsitsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

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
    private ArrayList<Recipes> RecList;
    private ArrayList<String> RecListkey;
    private imagRV_adapter ada;
    private SearchView searchView;
//    private ArrayList<Ingredients> IngList;


    public void HomeBtnClick(View view) {
        Intent intent = new Intent(RecommendedActivity.this, MainActivity.class);
        intent.putExtra("isAdmin",IsAdmin);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);

        initData();

        SetAdapter(RecList);

        GetDataFromFirebase();

//        if (IngList != null) {
//            filterbyIng();
//        }

        search();



    }

//    private void filterbyIng() {
//        ArrayList<Recipes> afterfilter = new ArrayList<>();
//        afterfilter.addAll(RecList);
//        for (Recipes R:RecList) {
//            for (Ingredients I: IngList ){
//                if(!R.getRecipeIngredients().contains(I.getName())){
//                    afterfilter.remove(R);
//                }
//            }
//        }
//
//        SetAdapter(afterfilter);
//    }

    private void search() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Recipes> SearchRes = new ArrayList<>();
                for (Recipes rec: RecList){
                    if(rec.getRecipeName().toLowerCase().contains(newText.toLowerCase())){
                        SearchRes.add(rec);
                    }
                }
                SetAdapter(SearchRes);
                ada.notifyDataSetChanged();
                return false;
            }
        });

    }

    private void GetDataFromFirebase() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Recipes r = dataSnapshot.getValue(Recipes.class);
                    if ((r.isApproved()|| IsAdmin) && !RecListkey.contains(r.getKey())) {
                        RecList.add(r);
                        RecListkey.add(r.getKey());
                    }
                }

                ada.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void SetAdapter(ArrayList<Recipes> arrList) {
        ada = new imagRV_adapter(arrList,RecommendedActivity.this, IsAdmin);
        RecRV.setAdapter(ada);
    }

    private void initData() {

        IsAdmin = getIntent().getExtras().getBoolean("isAdmin");
//        IngList = getIntent().getExtras().getParcelableArrayList("IngList");
        database = FirebaseDatabase.getInstance().getReference("recipes");


        RecRV = findViewById(R.id.recRV);
        RecRV.setLayoutManager(new LinearLayoutManager(this));
        searchView = findViewById(R.id.rspSearchView);

        RecList  = new ArrayList<>();
        RecListkey  = new ArrayList<>();


    }
}