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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hp_pc.niyoktahr.fragments.experience_constructor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by hp-pc on 8/17/2018.
 */

public class experience extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    ViewPager viewPager;
    RadioGroup radioGroup;
    RadioButton radio_fresher, radio_experience;
    Button done_btn;
    int exp=2;
    public String year, month, description_job, experience;
    EditText yearText, monthText, descriptionText;
    LinearLayout experience_layout;
    private FirebaseDatabase firebaseDatabase;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.prefrences_tab, container, false);

        done_btn = (Button) rootView.findViewById(R.id.form_exp_doneBTN);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.form_exp_Radiogroup);
        radio_fresher = (RadioButton) rootView.findViewById(R.id.form_fresher_radiobutton);
        radio_experience = (RadioButton) rootView.findViewById(R.id.form_exp_radiobutton);

        yearText = (EditText) rootView.findViewById(R.id.form_exp_year);
        monthText = (EditText) rootView.findViewById(R.id.form_exp_month);
        descriptionText = (EditText) rootView.findViewById(R.id.form_exp_description);

        experience_layout = (LinearLayout) rootView.findViewById(R.id.experienced_column);


        firebaseAuth = FirebaseAuth.getInstance();

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        // add database for the experienced part here
                        //year , month and description
                        sendUserDetails();
                        viewPager = (ViewPager) getActivity().findViewById(
                                R.id.container);
                        viewPager.setCurrentItem(3);





            }


        });

        //settin up experience buttons

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                switch (checkedId) {
                    case R.id.form_exp_radiobutton:
                        //visible the desc and year/months
                        experience_layout.setVisibility(View.VISIBLE);

                        break;

                    case R.id.form_fresher_radiobutton:
                        //invisible the desc and year/months
                        experience_layout.setVisibility(View.GONE);

                        break;
                }


            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("employee").child(firebaseAuth.getCurrentUser().getUid()).child("Experience details");
        ValueEventListener valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    experience_constructor userProfile = dataSnapshot.getValue(experience_constructor.class);
                    Log.e("Here ", userProfile + "    ll");

                    yearText.setText(userProfile.getYear());
                    monthText.setText(userProfile.getMonth());
                    descriptionText.setText(userProfile.getDescription_job());
                    //radio_experience.setChecked(true);

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
        // public String   year , month , description_job;
        year = yearText.getText().toString();
        month = monthText.getText().toString();
        description_job = descriptionText.getText().toString();
        experience = radio_fresher.getText().toString();
        experience_constructor userProfile = new experience_constructor(year, month, description_job, experience);
        myRef.child(firebaseAuth.getCurrentUser().getUid()).child("Experience details").setValue(userProfile);
    }
    private boolean is_filled(){
        String year = yearText.getText().toString();
        String month = monthText.getText().toString();
        String Desc = descriptionText.getText().toString();

        if(year.isEmpty()){
            return false;
        }
        if(month.isEmpty() || Integer.parseInt(month)>12 ){
            return false;
        }
        if(Desc.isEmpty() ){
            return false;
        }
        return true;
    }


}

