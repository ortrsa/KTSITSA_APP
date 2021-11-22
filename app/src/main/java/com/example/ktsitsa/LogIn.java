package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {
EditText TxtUser, TxtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        TxtUser= (EditText)findViewById(R.id.TxtUserNameAdd);
        TxtPassword= (EditText)findViewById(R.id.TxtPasswordAdd);
        String User =TxtUser.getText().toString();
        String Password =TxtPassword.getText().toString();


    }


    public void HomeBtnClick(View view) {
        Intent intent = new Intent(LogIn.this, MainActivity.class);
        startActivity(intent);

    }

    public void sign_in_click(View view) {
    }

    public void sign_up_click(View view) {
    }
}