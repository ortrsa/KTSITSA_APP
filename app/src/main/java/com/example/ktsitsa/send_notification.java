package com.example.ktsitsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class send_notification extends AppCompatActivity {


    private Button notify_button;
    private DatabaseReference database;
    private ArrayList<String> RecList;
    private String RandRec, RandTitle, RandMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);

        initData();
        getDatafromFirebase();
        randomData();
        send();



    }

    private void randomData() {




    }

    private void getDatafromFirebase() {
        database.addValueEventListener(new ValueEventListener() {
            //checks if data has been changed on firebase and update if it did
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Recipes r = dataSnapshot.getValue(Recipes.class);
                    if ((r.isApproved()) && r.isRecommended()) {
                        RecList.add(r.getRecipeName());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void send() {
        notify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RecList.size()>0) {

                    int index = (int) (Math.random() * RecList.size());
                    RandRec = RecList.get(index);

                    FcmNotificationsSender fsend = new FcmNotificationsSender("/topics/all", "המלצת היום בקציצה!", "נראה לנו שאנחנו יודעים מה בא לכם היום\n " + RandRec + " מעולה" +"\nעכשיו במומלצים", getApplicationContext(), send_notification.this);
                    fsend.SendNotifications();
                }
            }
        });
    }

    private void initData() {
        RecList = new ArrayList<>();
        notify_button = findViewById(R.id.send_notify_btn);
        database = FirebaseDatabase.getInstance().getReference("recipes");

    }


    //Returns to main menu screen
    public void HomeBtnClick(View view) {
        Intent intent = new Intent(send_notification.this, MainActivity.class);
        startActivity(intent);
    }
}