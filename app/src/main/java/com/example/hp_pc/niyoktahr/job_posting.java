package com.example.hp_pc.niyoktahr;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static com.example.hp_pc.niyoktahr.R.id.job_post_location;

public class job_posting extends AppCompatActivity {
    public EditText post_designation,post_company,post_vacancy,post_salary,post_describtion,post_email,post_location,post_enddate;
    ImageButton post_calender;
    private Button btsubmit;
    FirebaseAuth mFirebaseAuth;
    public String designation,company,vacancy,salary,describtion,job_post_email,location,enddate;
    private DatePickerDialog.OnDateSetListener dateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_posting);
        post_designation = (EditText) findViewById(R.id.job_post_designation);
        post_company = (EditText) findViewById(R.id.job_post_companyname);
        post_vacancy = (EditText) findViewById(R.id.job_post_vacancies);
        post_salary = (EditText) findViewById(R.id.job_post_salary);
        post_describtion = (EditText) findViewById(R.id.job_post_description);
        post_email = (EditText) findViewById(R.id.job_post_email);
        post_location = (EditText) findViewById(job_post_location);
        post_enddate = (EditText) findViewById(R.id.job_post_endDate);
        btsubmit=(Button)findViewById(R.id.job_post_doneBTN);
        post_calender=(ImageButton)findViewById(R.id.job_post_calendar);

        mFirebaseAuth=FirebaseAuth.getInstance();
      /* BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_naviagtion_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_home:
                       // Toast.makeText(MainActivity.this, "applied", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_apply:
                        //Toast.makeText(MainActivity.this, "applied", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(job_posting.this,job_posted.class));
                        break;
                    case R.id.action_profile:
                        //Toast.makeText(MainActivity.this, "applied", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(job_posting.this,settings.class));
                        break;


                }
                return true;
            }
    });*/

        post_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //  DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(),
                //        android.R.style.Theme_Material_Dialog_Alert,dateSetListener,year,month,day);

                DatePickerDialog datePickerDialog = new DatePickerDialog(job_posting.this,
                        android.R.style.Theme_Material_Dialog_Alert,dateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });


        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String DATE = day+"/"+month+"/"+year;
                post_enddate.setText(DATE);

            }
        };

        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!all_fields()) {
                    Toast.makeText(getApplicationContext(),"fill info correctly" , Toast.LENGTH_SHORT ).show();
                }

                else{
                    sendUserDetails();
                    sendDetails();
                    Toast.makeText(job_posting.this, "Submited", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(job_posting.this,job_posted.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }

            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_naviagtion_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()

        {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.action_home:

                        Toast.makeText(job_posting.this, "home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_postjobs:

                        Toast.makeText(job_posting.this, "posted jobs", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(job_posting.this,job_posted.class));

                        break;
                    case R.id.action_profile:
                        Toast.makeText(job_posting.this, "settings", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(job_posting.this, employer_settings.class));
                        break;


                }
                return true;
            }
        });

    }
    private void sendUserDetails() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("jobs");
//        public String designation,company,vacancy,salary,describtion,job_post_email,location,enddate;

        designation = post_designation.getText().toString();
         String userid= mFirebaseAuth.getCurrentUser().getUid();
        company = post_company.getText().toString();
        salary = post_salary.getText().toString();
        vacancy = post_vacancy.getText().toString();
        describtion = post_describtion.getText().toString();
        job_post_email = post_email.getText().toString();
        location = post_location.getText().toString();
        enddate = post_enddate.getText().toString();

        jobpost_constructor userProfile = new jobpost_constructor(designation,company,vacancy,salary,describtion,job_post_email,location,enddate,userid);
        myRef.push().setValue(userProfile);


    }
    private void sendDetails() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("job");

        designation = post_designation.getText().toString();
        String userid= mFirebaseAuth.getCurrentUser().getUid();
        company = post_company.getText().toString();
        salary = post_salary.getText().toString();
        vacancy = post_vacancy.getText().toString();
        describtion = post_describtion.getText().toString();
        job_post_email = post_email.getText().toString();
        location = post_location.getText().toString();
        enddate = post_enddate.getText().toString();

        jobpost_constructor userProfile = new jobpost_constructor(designation,company,vacancy,salary,describtion,job_post_email,location,enddate,userid);
        myRef.child(mFirebaseAuth.getCurrentUser().getUid()).push().setValue(userProfile);


    }
    private boolean all_fields(){
        String designation =post_designation.getText().toString();
        String companyname = post_company.getText().toString();
        String salary = post_salary.getText().toString();
        String vacancies = post_vacancy.getText().toString();
        String email =post_email.getText().toString();
        String location =post_location.getText().toString();
        String description =post_describtion.getText().toString();
        String date =post_enddate.getText().toString();

        if(vacancies.isEmpty()){
            return false;
        }

        if(salary.isEmpty()){
            return false;
        }

        if(companyname.isEmpty()){
            return false;
        }
        if(designation.isEmpty()){
            return false;
        }

        if(email.isEmpty()){
            return  false;
        }

        if(location.isEmpty()){
            return false;
        }
        if(description.isEmpty()  || description.length()>75 ){
            return false;
        }

        if(date.isEmpty()){
            return false;
        }


        return true;
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
