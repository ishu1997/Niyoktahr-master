package com.example.hp_pc.niyoktahr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Secondpage extends AppCompatActivity {
    EditText education, skills, jobs;
    Button button, btsumbit;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    String meducation, mskills, mjobs;


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthStateListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);
        education = (EditText) findViewById(R.id.etEduaction);
        skills = (EditText) findViewById(R.id.etSkills);
        jobs = (EditText) findViewById(R.id.etJobs);
        button = (Button) findViewById(R.id.logout);
        btsumbit = (Button) findViewById(R.id.btEdusumbit);
        firebaseAuth = FirebaseAuth.getInstance();
              meducation=education.getText().toString();


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(Secondpage.this, MainActivity.class));
                }
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
            }
        });
        btsumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserDetails();
                Toast.makeText(Secondpage.this, "yesss", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(Secondpage.this, MainActivity.class));
            }
        });


    }

    private void sendUserDetails() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        meducation = education.getText().toString();

        mskills = skills.getText().toString();
        mjobs = jobs.getText().toString();
       // UserProfile userProfile = new UserProfile(meducation, mskills, mjobs);
       // myRef.setValue(userProfile);


    }


}


