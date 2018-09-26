package com.example.hp_pc.niyoktahr.fragments;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hp_pc.niyoktahr.R;
import com.example.hp_pc.niyoktahr.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by hp-pc on 8/17/2018.
 */

public class Personal_detailstab extends Fragment {



    public String  name , email , location, phoneno , dob;
    FirebaseAuth firebaseAuth;
    ViewPager viewPager;
    Button Done_personal;
    EditText personal_name , personal_email , personal_location, personal_phone , personal_dob;
    ImageButton calendar_Button;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    FirebaseDatabase firebaseDatabase;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
        personal_name = (EditText)rootView.findViewById(R.id.personal_name_Etext);
        personal_email = (EditText)rootView.findViewById(R.id.personal_email_Etext);
        personal_location = (EditText)rootView.findViewById(R.id.personal_location_Etext);
        personal_phone = (EditText)rootView.findViewById(R.id.personal_phone_Etext);
        personal_dob = (EditText)rootView.findViewById(R.id.personal_dob_Etext);
        Done_personal = (Button)rootView.findViewById(R.id.form_personal_doneBTN);
        calendar_Button = (ImageButton)rootView.findViewById(R.id.form_personal_calendar);

        // calendar lagic here

        calendar_Button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        android.R.style.Theme_Material_Dialog_Alert,dateSetListener,year,month,day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String DOB = day+"/"+month+"/"+year;
                personal_dob.setText(DOB);

            }
        };


        //done button logic here----



        viewPager = (ViewPager) getActivity().findViewById(R.id.container);


        firebaseAuth = FirebaseAuth.getInstance();


        Done_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!all_fields()) {
                    Toast.makeText(getActivity(),"fill info correctly" , Toast.LENGTH_SHORT ).show();
                }

                else{
                    sendUserDetails();
                    viewPager = (ViewPager) getActivity().findViewById(
                            R.id.container);
                    viewPager.setCurrentItem(1);


                    Toast.makeText(getActivity(),"information sumbited",Toast.LENGTH_SHORT).show();
                }
                }

        });
        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("employee").child(firebaseAuth.getCurrentUser().getUid()).child("Personal details");

        ValueEventListener valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                   // UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                    //Log.e("Here ", userProfile + "    ll");
                   if (dataSnapshot.exists()) {
                       UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                       Log.e("Here ", userProfile + "    ll");

                        personal_name.setText(userProfile.getName());
                        personal_email.setText(userProfile.getEmail());
                        personal_location.setText(userProfile.getLocation());
                        personal_phone.setText(userProfile.getPhoneno());
                        personal_dob.setText(userProfile.getDob());
                        //profilequal.setText(userProfile.getQualifications());

                    }

               }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                 //   Toast.makeText(getActivity(), "error occurred", Toast.LENGTH_SHORT).show();
                }
            });




        return rootView;


    }
    private void sendUserDetails() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("employee");
       // personal_name , personal_email , personal_location, personal_phone , personal_dob;
        name = personal_name.getText().toString();

        email = personal_email.getText().toString();
        location = personal_location.getText().toString();
        phoneno = personal_phone.getText().toString();
        dob = personal_dob.getText().toString();
        UserProfile userProfile = new UserProfile(  name , email , location, phoneno , dob);

        myRef.child(firebaseAuth.getCurrentUser().getUid()).child("Personal details").setValue(userProfile);


    }

    private boolean all_fields(){
        String name =personal_name.getText().toString();
        String email =personal_email.getText().toString();
        String location =personal_location.getText().toString();
        String phone =personal_phone.getText().toString();
        String dob =personal_dob.getText().toString();



        if(name.isEmpty()){
            return false;
        }

        if(email.isEmpty()){
            return  false;
        }

        if(location.isEmpty()){
            return false;
        }
        if(phone.isEmpty() || phone.length()<10 ){
            return false;
        }
        if(dob.isEmpty()){
            return false;
        }


        return true;
    }






    }

