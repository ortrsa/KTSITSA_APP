package com.example.ktsitsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;

public class LogIn extends AppCompatActivity {


    private EditText TxtUser, TxtPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        TxtUser= (EditText)findViewById(R.id.TxtEmailAdd);
        TxtPassword= (EditText)findViewById(R.id.TxtPasswordAdd);
        String User =TxtUser.getText().toString();
        String Password =TxtPassword.getText().toString();
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if the user is allredy in (dident sign out)
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){

            DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid()).child("Admin");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String admins = snapshot.getValue().toString();
                    if(admins.equals("1")) {
                        Toast.makeText(LogIn.this, "login as Admin!", Toast.LENGTH_LONG).show();
                        Intent Aintent = new Intent(LogIn.this, MainActivity.class);
                        Aintent.putExtra("isAdmin", true);
                        startActivity(Aintent);

                    }else{
                        Intent NAintent = new Intent(LogIn.this, MainActivity.class);
                        NAintent.putExtra("isAdmin", false);
                        startActivity(NAintent);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }
    }


    public void HomeBtnClick(View view) {


    }

    public void sign_up_click(View view) {
        // takes the txt from the login lins and makes them to string
        EditText email = (EditText) findViewById(R.id.TxtEmailAdd);
        String StrEmail = email.getText().toString();
        EditText Password = (EditText) findViewById(R.id.TxtPasswordAdd);
        String StrPassword = Password.getText().toString();

        mAuth.createUserWithEmailAndPassword(StrEmail,StrPassword).addOnCompleteListener(LogIn.this , new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {// אם מצליח

                        Intent NAintent = new Intent(LogIn.this, MainActivity.class);
                        NAintent.putExtra("isAdmin", false);
                        startActivity(NAintent);

                        String uid = mAuth.getCurrentUser().getUid();
                        database = FirebaseDatabase.getInstance().getReference("users").child(uid).child("Admin");
                        database.setValue("0");

                    } else {// אם לא מצליח
                        Toast.makeText(LogIn.this, "sign-in failed", Toast.LENGTH_LONG).show();// הודעה
                    }
                }
            });
        }


    public void sign_in_click(View view) {
        EditText email = (EditText) findViewById(R.id.TxtEmailAdd);
        String StrEmail = email.getText().toString();
        EditText Password = (EditText) findViewById(R.id.TxtPasswordAdd);
        String StrPassword = Password.getText().toString();

        mAuth.signInWithEmailAndPassword(StrEmail,StrPassword).addOnCompleteListener(LogIn.this , new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid()).child("Admin");
                    database.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String admins = snapshot.getValue().toString();
                            if(admins.equals("1")) {
                                Toast.makeText(LogIn.this, "login as Admin!", Toast.LENGTH_LONG).show();
                                Intent Aintent = new Intent(LogIn.this, MainActivity.class);
                                Aintent.putExtra("isAdmin", true);
                                startActivity(Aintent);

                            }else{
                                Intent NAintent = new Intent(LogIn.this, MainActivity.class);
                                NAintent.putExtra("isAdmin", false);
                                startActivity(NAintent);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                } else {// אם לא מצליח
                    Toast.makeText(LogIn.this, "log-in failed", Toast.LENGTH_LONG).show();// הודעה
                }
            }
        });
    }

}