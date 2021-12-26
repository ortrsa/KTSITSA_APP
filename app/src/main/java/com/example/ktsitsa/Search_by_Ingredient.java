
package com.example.ktsitsa;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.SparseBooleanArray;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.SearchView;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;

public class Search_by_Ingredient extends AppCompatActivity {


    private ListView ListViewData;
    private ArrayAdapter<Ingredients> adapter;
    private DatabaseReference db;
    private Button button;
    private Button filter_button;
    private Boolean IsAdmin;
    private ArrayList<Ingredients> selectedList,ingList ;
    private ArrayList<String> setOfCategories, filterList;
    private SearchView searchFiled;
    private ArrayList<Integer> curseList;
    private boolean[] checkList;
    private String[] s;
    private ArrayList<Ingredients> filterRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_ingredient);

        initData();

        setadapter(ingList);

        getDataFromFirebase();

        search_Filed();

        Filter_button_click();

        Ok_button_click();



    }

    private void search_Filed() {

        searchFiled.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Ingredients> SearchRes = new ArrayList<>();
                for (Ingredients ing: ingList){
                    if(ing.getName().toLowerCase().contains(newText.toLowerCase())){
                        SearchRes.add(ing);
                    }
                }
                setadapter(SearchRes);
                adapter.notifyDataSetChanged();
                return false;
            }
        });



    }

    private void Ok_button_click() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray sp = ListViewData.getCheckedItemPositions();

                selectedList= new ArrayList<>();

                for(int i=0;i<sp.size();i++){
                    if(sp.valueAt(i)==true){
                        Ingredients Ing= (Ingredients) ListViewData.getItemAtPosition(sp.keyAt(i));
                        selectedList.add(Ing);
                    }
                }
                if(sp.size()!=0) {
                    Intent intent = new Intent(Search_by_Ingredient.this, ResultsSearchByIngActivity.class);
                    intent.putExtra("isAdmin", IsAdmin);
                    intent.putExtra("IngList", selectedList);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Search_by_Ingredient.this, "בחר לפחות מרכיב אחד ", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void Filter_button_click() {
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Search_by_Ingredient.this);
                builder.setTitle(" סינון קטגוריות ");
                builder.setCancelable(false);


                builder.setMultiChoiceItems(s, checkList, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        if(isChecked){
                            curseList.add(which);
                            Toast.makeText(Search_by_Ingredient.this, s[which], Toast.LENGTH_SHORT).show();
                        }else {
                            curseList.remove((Object)which);
                        }
                    }
                }).setPositiveButton("אישור", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filterRes = new ArrayList<>();

                        for (int i = 0; i < ingList.size(); i++) {
                            for (int j = 0; j < curseList.size(); j++) {

                                if(ingList.get(i).getCategory().equals(s[curseList.get(j)])){
                                    filterRes.add(ingList.get(i));
                                    break;
                                }
                            }

                        }
                        if(filterRes.size()>0){
                            setadapter(filterRes);
                        }else {
                            setadapter(ingList);
                        }
                    }
                }).setNegativeButton("ביטול", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNeutralButton("נקה הכל", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            }
        });
    }

    private void getDataFromFirebase() {

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Ingredients ing = dataSnapshot.getValue(Ingredients.class);
                    if(!setOfCategories.contains(ing.getCategory())) {
                        setOfCategories.add(ing.getCategory());
                    }


                    ingList.add(ing);

                }
                s = new String[setOfCategories.size()];
                for (int i = 0; i < setOfCategories.size(); i++) {
                    s[i] = setOfCategories.get(i);
                }
                checkList = new boolean[setOfCategories.size()+1];
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setadapter(ArrayList<Ingredients> arr) {
        adapter = new ArrayAdapter<Ingredients>(this,
                android.R.layout.simple_list_item_multiple_choice, arr);
        ListViewData.setAdapter(adapter);

    }

    private void initData() {

        // the user is admin?
        IsAdmin = getIntent().getExtras().getBoolean("isAdmin");


        //connect to firebase
        db = FirebaseDatabase.getInstance().getReference("Ingredients");

        // init View
        button = findViewById(R.id.BtnLVIng);
        ListViewData = findViewById(R.id.ListViewIng);
        filter_button = findViewById(R.id.filterB);
        searchFiled = findViewById(R.id.ingSearchView);
        ingList = new ArrayList<>();
        setOfCategories = new ArrayList<>();
        curseList = new ArrayList<>();
        filterList =new ArrayList<>();



    }


}