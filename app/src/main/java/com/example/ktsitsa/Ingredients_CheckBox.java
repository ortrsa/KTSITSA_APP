package com.example.ktsitsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class Ingredients_CheckBox extends AppCompatActivity {


    private ListView ListViewData;
    private ArrayAdapter<Ingredients> adapter;
    private DatabaseReference db;
    private Button button;
    private Boolean IsAdmin;
    private ArrayList<Ingredients> selectedList;
    private ArrayList<Ingredients>  ingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_check_box);
        IsAdmin = getIntent().getExtras().getBoolean("isAdmin");

        //connect to firebase
        db = FirebaseDatabase.getInstance().getReference("Ingredients");

        // init View
        button = findViewById(R.id.BtnLVIng);
        ListViewData = findViewById(R.id.ListViewIng);

        // set adapter
        ingList = new ArrayList<>();
        adapter = new ArrayAdapter<Ingredients>(this,
                android.R.layout.simple_list_item_multiple_choice, ingList);
        ListViewData.setAdapter(adapter);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Ingredients ing = dataSnapshot.getValue(Ingredients.class);
                    ingList.add(ing);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray sp = ListViewData.getCheckedItemPositions();

                selectedList= new ArrayList<>();

                for(int i=0;i<sp.size();i++){
                    if(sp.valueAt(i)==true){
                        Ingredients Ing= (Ingredients) ListViewData.getItemAtPosition(i);
                        selectedList.add(Ing);
                    }
                }
                if(sp.size()!=0) {
                    Intent intent = new Intent(Ingredients_CheckBox.this, AddRecipes.class);
                    intent.putExtra("isAdmin", IsAdmin);
                    intent.putExtra("IngList", selectedList);
                    startActivity(intent);
                }else {
                    Toast.makeText(Ingredients_CheckBox.this, "בחר לפחות מרכיב אחד ", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }



}