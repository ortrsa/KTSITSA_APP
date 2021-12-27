package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class send_notification extends AppCompatActivity {


    private Button notify_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);

        initData();
        send();




    }

    private void send() {
        notify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FcmNotificationsSender fsend = new FcmNotificationsSender("/topics/all","title","mssege",getApplicationContext(),send_notification.this);
                fsend.SendNotifications();
            }
        });
    }

    private void initData() {
        notify_button = findViewById(R.id.send_notify_btn);
    }


    //Returns to main menu screen
    public void HomeBtnClick(View view) {
        Intent intent = new Intent(send_notification.this, MainActivity.class);
        startActivity(intent);
    }
}