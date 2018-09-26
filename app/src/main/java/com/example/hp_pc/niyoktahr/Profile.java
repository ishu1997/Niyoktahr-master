package com.example.hp_pc.niyoktahr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp_pc.niyoktahr.fragments.Education_constructor;
import com.example.hp_pc.niyoktahr.fragments.Fill_details;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private TextView profileeduaction,profileskills,profilejobs, personal_location, personal_dob;
   private TextView qualification_edu , language_edu , skills_edu , passing_year_edu ,college_edu;
    private Button profileUpdate;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //profilepic=(ImageView)findViewById(R.id.ivProfilepic);
        profileeduaction=(TextView)findViewById(R.id.employee_profile_name);
        profileskills=(TextView)findViewById(R.id.employee_profile_email);
        personal_location=(TextView)findViewById(R.id.employee_profile_location);
        personal_dob=(TextView)findViewById(R.id.employee_profile_dob);
        profilejobs=(TextView)findViewById(R.id.employee_profile_contact);

        qualification_edu = (TextView)findViewById(R.id.employee_profile_qualification);
        skills_edu  =(TextView)findViewById(R.id.employee_profile_skills);
        language_edu = (TextView)findViewById(R.id.employee_profile_language);
        college_edu = (TextView)findViewById(R.id.employee_profile_college);
        passing_year_edu = (TextView)findViewById(R.id.employee_profile_passing_year);
        profileUpdate=(Button)findViewById(R.id.bteditprofile);
        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,Fill_details.class));
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

                   DatabaseReference databaseReference=firebaseDatabase.getReference("employee").child(firebaseAuth.getCurrentUser().getUid()).child("Personal details");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
                    profileeduaction.setText(userProfile.getName());
                    profileskills.setText(userProfile.getEmail());
                    profilejobs.setText(userProfile.getPhoneno());
                    personal_location.setText(userProfile.getLocation());
                    personal_dob.setText(userProfile.getDob());
                    //profilequal.setText(userProfile.getQualifications());
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Profile.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        DatabaseReference database=firebaseDatabase.getReference("employee").child(firebaseAuth.getCurrentUser().getUid()).child("eduaction details");
        ValueEventListener valueEventListener = database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Education_constructor userProfile = dataSnapshot.getValue(Education_constructor.class);
                    Log.e("Here ", userProfile + "    ll");
                    qualification_edu.setText(userProfile.getQualification());
                    skills_edu.setText(userProfile.getSkills());
                    language_edu.setText(userProfile.getLanguage());
                    college_edu.setText(userProfile.getCollege());
                    passing_year_edu.setText(userProfile.getPassing_year());
                    //profilequal.setText(userProfile.getQualifications());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Toast.makeText(Educational_detailstab.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    }
