package com.example.hp_pc.niyoktahr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hp_pc.niyoktahr.employer_fragment.Employer_detail;
import com.example.hp_pc.niyoktahr.fragments.Fill_details;
import com.google.firebase.auth.FirebaseAuth;

public class employee_or_employer extends AppCompatActivity {
    Button employee;
    Button employer;
 FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_or_employer);
        employee=(Button)findViewById(R.id.btemployee);
        employer=(Button)findViewById(R.id.btemployer);
        mAuth=FirebaseAuth.getInstance();
        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                //DatabaseReference myRef = firebaseDatabase.getReference("employee");
                //String userid= mAuth.getCurrentUser().getUid();
              //  jobpost_constructor userProfile = new jobpost_constructor(designation,company,vacancy,salary,describtion,job_post_email,location,enddate,userid);
                //myRef.child(mAuth.getCurrentUser().getUid()).setValue(userid);


                startActivity(new Intent(employee_or_employer.this,Fill_details.class));
            }
        });
        employer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(employee_or_employer.this,Employer_detail.class));
            }
        });
    }
}
