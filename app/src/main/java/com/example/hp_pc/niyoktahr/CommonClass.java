package com.example.hp_pc.niyoktahr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.hp_pc.niyoktahr.fragments.Education_constructor;
import com.example.hp_pc.niyoktahr.fragments.Fill_details;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by hp-pc on 9/23/2018.
 */

public class CommonClass extends AppCompatActivity {


    private FirebaseAuth mAuth;
    public Context mContext;

    public void CheckingEmployeePersonalInfo() {

        mAuth = FirebaseAuth.getInstance();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("employee").child(mAuth.getCurrentUser().getUid()).child("Personal details");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);

                Log.e("hi", userProfile + "       hfv");

                if (userProfile == null) {

                    Log.e(" here come s here", " here");
                    gettingEmployerDetails();


                } else {
                    checkingEducationDetails();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    private void checkingEducationDetails() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("employee").child(mAuth.getCurrentUser().getUid()).child("eduaction details");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Education_constructor userProfile = dataSnapshot.getValue(Education_constructor.class);

                Log.e("hi", userProfile + "       hfv");

                if (userProfile == null) {

                    Log.e(" here come s here", " here");
                    // take to education page
                    if (MainActivity.running)
                        startActivityForResult(new Intent(CommonClass.this, Fill_details.class), MainActivity.educationCallback);


                } else {
                    //startActivity(new Intent(MainActivity.this, job_posted.class));
                    // startActivity(new Intent(MainActivity.this, Secondpage.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    private void gettingEmployerDetails() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("employer").child(mAuth.getCurrentUser().getUid()).child("Personal details");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                employer1_constructor userProfile = dataSnapshot.getValue(employer1_constructor.class);

                Log.e("hi", userProfile + "       hfv");

                if (userProfile == null) {

                    Log.e(" here come s here", " here");
                    // take to education page
                    if(MainActivity.running)
                        startActivityForResult(new Intent(CommonClass.this, employee_or_employer.class), MainActivity.chooseScreenCallback);

                } else {

                    if(MainActivity.running)
                        startActivityForResult(new Intent(CommonClass.this, employer_verification.class), MainActivity.employerCallback);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
