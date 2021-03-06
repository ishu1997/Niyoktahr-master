package com.example.hp_pc.niyoktahr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class employer_settings extends AppCompatActivity {
    TextView profile,password,logout,aboutus;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_settings);
        profile=(TextView)findViewById(R.id.tvsettingProfile);
        password=(TextView)findViewById(R.id.tvchangePassword);
        logout=(TextView)findViewById(R.id.tvLogout);
        aboutus=(TextView)findViewById(R.id.tvaboutus);
        mFirebaseAuth=FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    Intent intent = new Intent(employer_settings.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(employer_settings.this,UpdatePassword.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(employer_settings.this,AboutUs.class));
            }
        });
    }
}
