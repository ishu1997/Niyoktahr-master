package com.example.hp_pc.niyoktahr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends CommonClass {


    FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 2;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    UserProfile userProfile;
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabaseReference;
    public String remove;
    private ArrayList<String> appliedLists = new ArrayList<>();
    public ArrayList<jobpost_constructor> allJobsData = new ArrayList<>();
    public static boolean running = true;
    public static final int employerCallback = 1001;
    public static final int educationCallback = 1002;
    public static final int chooseScreenCallback = 1003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondone);
        running = true;
        //loadindScreen();
    }

    private void loadindScreen() {

        running = true;
        basicInitialization();
        authInitialization();
        opening();
    }

    private void basicInitialization() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_naviagtion_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_apply:
                        startActivity(new Intent(MainActivity.this, employee_applied_jobs.class));
                        Toast.makeText(MainActivity.this, "applied jobs", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.action_profile:
                        Toast.makeText(MainActivity.this, "settings", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, settings.class));
                        break;


                }
                return true;
            }
        });
    }

    private void authInitialization() {
        mAuth = FirebaseAuth.getInstance();
        // final String userid= mAuth.getCurrentUser().getUid();
        //   String eventid=FirebaseDatabase.getInstance().getReference().child("jobs").push().getKey();

        if (mAuth.getCurrentUser() != null) {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("employee").child(mAuth.getCurrentUser().getUid()).child("applied");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        // TODO: handle the post

                        //jobpost_constructor applied = postSnapshot.getValue(jobpost_constructor.class);
                        Log.e("Here applied ", postSnapshot.getKey() + "  ");
                        appliedLists.add(postSnapshot.getKey());
                    }

                    settingJobsData();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }

    private void settingJobsData() {

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("jobs");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    jobpost_constructor jobpostConstructor = dataSnapshot1.getValue(jobpost_constructor.class);

                    Log.e(" applied data", appliedLists.toString());
                    Log.e(" applied new", dataSnapshot1.getKey());

                    if (!appliedLists.contains(dataSnapshot1.getKey())) {

                        jobpostConstructor.setJobId(dataSnapshot1.getKey());
                        allJobsData.add(jobpostConstructor);
                    }


                    Log.e(" after applied", allJobsData.size() + "   ll");
                }

                settingAdapter();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        loadindScreen();
    }

    private void settingAdapter() {
        mRecyclerView = (RecyclerView) findViewById(R.id.main_job_post);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        JobsAdapter jobsAdapter = new JobsAdapter(MainActivity.this, allJobsData);
        mRecyclerView.setAdapter(jobsAdapter);
    }


    public static class jobViewHolder extends RecyclerView.ViewHolder {
        View mView;
        Button mButton;

        public jobViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            mButton = (Button) itemView.findViewById(R.id.btapllied);

        }


        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.tvjobTitle);
            post_title.setText(title);
        }

        public void setProfession(String profession) {
            TextView post_profession = (TextView) mView.findViewById(R.id.tvcompanyName);
            post_profession.setText(profession);
        }

        public void setJob(String job) {
            TextView post_job = (TextView) mView.findViewById(R.id.tvLocation);
            post_job.setText(job);
        }

        public void setJoblocation(String job) {
            TextView post_job = (TextView) mView.findViewById(R.id.tv_job_location);
            post_job.setText(job);
        }

        public void setJobdescription(String job) {
            TextView post_job = (TextView) mView.findViewById(R.id.job_description);
            post_job.setText(job);
        }


    }

    private void opening() {

        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        Log.e("here firebase ", firebaseUser + "       ll");
        if ((firebaseUser != null)) {

            //finish();
            //Log.e(" Here sourabh", FirebaseDatabase.getInstance().getReference(mAuth.getCurrentUser().getUid()) + "   kk");
            // second page  = edit educatiom
            // second one  =  hello

            Log.e(" value here", FirebaseDatabase.getInstance().getReference(mAuth.getCurrentUser().getUid()) + "     l");

            CheckingEmployeePersonalInfo();


        } else {

                startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), 27);
        }
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case educationCallback:
                if (resultCode == RESULT_OK) {
                    String value = (String) data.getExtras().getString("success");
                    if (value.equals("true")) {
                        loadindScreen();
                        return;
                    }
                }
                onBackPressed();
                break;

            case chooseScreenCallback:

                if (resultCode == RESULT_OK) {
                    String value = (String) data.getExtras().getString("success");
                    if (value.equals("true")) {
                        loadindScreen();
                        return;
                    }
                }
                onBackPressed();

                break;

            case employerCallback:
                onBackPressed();
                break;

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profileMenu: {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
