package com.example.hp_pc.niyoktahr.employer_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp_pc.niyoktahr.R;
import com.example.hp_pc.niyoktahr.job_posting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hp-pc on 8/24/2018.
 */

public class employer2 extends Fragment {
    EditText link,desc;
    Button btsumbit;
    FirebaseAuth firebaseAuth;
    public String companylink;
    public String companyDescription;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.employer2, container, false);
        link = (EditText) rootView.findViewById(R.id.etCompanylink);
        desc = (EditText)rootView.findViewById(R.id.etCompanydesc);

        btsumbit = (Button)rootView. findViewById(R.id.btCompanySubmit);
        firebaseAuth = FirebaseAuth.getInstance();
        btsumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserDetails();

                  Intent intent = new Intent(getActivity(), job_posting.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"information sumbited",Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;
    }
    private void sendUserDetails() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("employer");
        companylink  = link.getText().toString();

        companyDescription = desc.getText().toString();

        employer2_constructor userProfile = new employer2_constructor(companylink,companyDescription);
        myRef.child(firebaseAuth.getCurrentUser().getUid()).child("Company details").setValue(userProfile);


    }
    }

