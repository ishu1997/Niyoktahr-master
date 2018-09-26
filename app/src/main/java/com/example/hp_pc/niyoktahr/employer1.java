package com.example.hp_pc.niyoktahr;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.hp_pc.niyoktahr.R.id.personal_email_employer;

/**
 * Created by hp-pc on 8/24/2018.
 */

public class employer1 extends Fragment {

    Button btsumbit;

    EditText employer_name , employer_post , company_name,company_website, contact_number,contact_email , location;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    //public String employer_name, employer_post, company_name, company_website, contact_number, contact_email, location;
    public String employer_form_name, employer_form_post, company_employer_name, company_employer_website, contact_employer_number, contact_employer_email, employer_location;


    ViewPager viewPager;


    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_employer_details, container, false);
        employer_name = (EditText)rootView.findViewById(R.id.personal_name_employer);
        employer_post = (EditText)rootView.findViewById(R.id.personal_post_employer);
        company_name = (EditText)rootView.findViewById(R.id.personal_companyname_employer);
        company_website = (EditText)rootView.findViewById(R.id.personal_companyURL_employer);
        contact_number = (EditText)rootView.findViewById(R.id.personal_phone_employer);
        contact_email = (EditText)rootView.findViewById(personal_email_employer);
        location = (EditText)rootView.findViewById(R.id.personal_location_employer);

        btsumbit = (Button)rootView. findViewById(R.id.form_employer_doneBTN);
        firebaseAuth = FirebaseAuth.getInstance();

        btsumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_filled()) {

                    Log.e(" Here comes", "true");

                    //Add code for firebase here to add data to the server
                    sendUserDetails();
                    Intent intent = new Intent(getActivity(), employer_verification.class);

                    intent.putExtra("NAME",employer_form_name);
                    intent.putExtra("PHONE",contact_employer_number);
                    intent.putExtra("LOCATION",employer_location);
                    intent.putExtra("Email",contact_employer_email);
                    intent.putExtra("website",company_employer_website);

                    startActivity(intent);
                    //  Intent intent = new Intent(getActivity(), job_posting.class);
                    // startActivity(intent);
                    Toast.makeText(getActivity(),"information sumbited",Toast.LENGTH_SHORT).show();



                }
                else{
                    Toast.makeText(getActivity(),"fill all fields correctly" , Toast.LENGTH_SHORT ).show();

                }
            }


        });
        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("employer").child(firebaseAuth.getCurrentUser().getUid()).child("Personal details");




        ValueEventListener valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //public String employer_name, employer_post, company_name, company_website, contact_number, contact_email, location;
                //public String employer_form_name, employer_form_post, company_employer_name, company_employer_website, contact_employer_number, contact_employer_email, employer_location;

                if (dataSnapshot.exists()) {
                    employer1_constructor userProfile = dataSnapshot.getValue(employer1_constructor.class);
                    Log.e("Here ", userProfile + "    ll");

                    employer_name.setText(userProfile.getEmployer_form_name());
                    employer_post.setText(userProfile.getEmployer_form_post());
                    company_name.setText(userProfile.getCompany_employer_name());
                    company_website.setText(userProfile.getCompany_employer_website());
                    contact_number.setText(userProfile.getContact_employer_number());
                    contact_email.setText(userProfile.getContact_employer_email());
                    location.setText(userProfile.getEmployer_location());

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
        DatabaseReference myRef = firebaseDatabase.getReference("employer");
        //public String employer_name, employer_post, company_name, company_website, contact_number, contact_email, location;
        //public String employer_form_name, employer_form_post, company_employer_name, company_employer_website, contact_employer_number, contact_employer_email, employer_location;

       employer_form_name = employer_name.getText().toString();
        employer_form_post = employer_post.getText().toString();
        company_employer_name= company_name.getText().toString();
        company_employer_website = company_website.getText().toString();
        contact_employer_number = contact_number.getText().toString();
        employer_location = location.getText().toString();
        contact_employer_email = contact_email.getText().toString();

        employer1_constructor userProfile = new employer1_constructor(employer_form_name, employer_form_post, company_employer_name, company_employer_website, contact_employer_number, contact_employer_email, employer_location);
        myRef.child(firebaseAuth.getCurrentUser().getUid()).child("Personal details").setValue(userProfile);


    }
    private boolean is_filled(){
        String E_name = employer_name.getText().toString();
        String E_post = employer_post.getText().toString();
        String C_name = company_name.getText().toString();
        String C_url = company_website.getText().toString();
        String E_phone = contact_number.getText().toString();
        String E_email = contact_email.getText().toString();
        String E_location = location.getText().toString();


        if(E_name.isEmpty()){
            return false;
        }

        if(E_post.isEmpty()){
            return false;
        }

        if(C_name.isEmpty()){
            return false;
        }
        if(C_url.isEmpty()){
            return false;
        }
        if(E_phone.isEmpty() || E_phone.length() != 10){
            return false;
        }
        if(E_email.isEmpty()){
            return false;
        }

        if(E_location.isEmpty()){
            return false;
        }


        return true;
    }




}

