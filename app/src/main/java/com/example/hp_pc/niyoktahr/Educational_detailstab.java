package com.example.hp_pc.niyoktahr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp_pc.niyoktahr.R;
import com.example.hp_pc.niyoktahr.fragments.Education_constructor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by hp-pc on 8/17/2018.
 */

public class Educational_detailstab extends Fragment {

    Button  btsumbit;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    public String qualification , language , skills , passing_year ,college;
    ViewPager viewPager;
    EditText qualification_edu , language_edu , skills_edu , passing_year_edu ,college_edu;
    Button donebtn;
    private FirebaseDatabase firebaseDatabase;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_education_details, container, false);
        qualification_edu = (EditText)rootView.findViewById(R.id.form_education_qualification);
        skills_edu  =(EditText)rootView.findViewById(R.id.form_education_skills);
        language_edu = (EditText)rootView.findViewById(R.id.form_education_language);
        college_edu = (EditText)rootView.findViewById(R.id.form_education_college);
        passing_year_edu = (EditText)rootView.findViewById(R.id.form_education_passingyear);

         donebtn = (Button) rootView.findViewById(R.id.from_education_doneBTN);


        firebaseAuth = FirebaseAuth.getInstance();

        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!all_fields()) {
                    Toast.makeText(getActivity(), "fill info correctly", Toast.LENGTH_SHORT).show();
                } else {
                    sendUserDetails();
                    viewPager = (ViewPager) getActivity().findViewById(
                            R.id.container);
                    viewPager.setCurrentItem(2);
                    Toast.makeText(getActivity(), "information sumbited", Toast.LENGTH_SHORT).show();

                }
            }

        });

       firebaseDatabase=FirebaseDatabase.getInstance();
     DatabaseReference databaseReference=firebaseDatabase.getReference("employee").child(firebaseAuth.getCurrentUser().getUid()).child("eduaction details");
        ValueEventListener valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
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

        return rootView;


    }
    private void sendUserDetails() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("employee");
        qualification  = qualification_edu.getText().toString();

        language = language_edu.getText().toString();
        skills= skills_edu.getText().toString();
        passing_year= passing_year_edu.getText().toString();
        college= college_edu.getText().toString();
        Education_constructor userProfile = new Education_constructor(qualification,language,skills,passing_year,college);
        myRef.child(firebaseAuth.getCurrentUser().getUid()).child("eduaction details").setValue(userProfile);


    }
    private boolean all_fields(){
        String college =college_edu.getText().toString();
        String language =language_edu.getText().toString();
        String qualification =qualification_edu.getText().toString();
        String passing_year =passing_year_edu.getText().toString();
        String skills =skills_edu.getText().toString();



        if(qualification.isEmpty()){
            return false;
        }

        if(college.isEmpty()){
            return  false;
        }

        if(language.isEmpty()){
            return false;
        }
        if(passing_year.isEmpty() || passing_year.length()!=4 ){
            return false;
        }
        if(skills.isEmpty()){
            return false;
        }


        return true;
    }


}


