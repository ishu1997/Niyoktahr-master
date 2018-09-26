package com.example.hp_pc.niyoktahr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class job_posted extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabaseReference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_posted);
        mAuth = FirebaseAuth.getInstance();
        // final String userid= mAuth.getCurrentUser().getUid();
        //   String eventid=FirebaseDatabase.getInstance().getReference().child("jobs").push().getKey();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("job").child(mAuth.getCurrentUser().getUid());

        mDatabaseReference.keepSynced(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_job_posted);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerAdapter<jobpost_constructor, job_posted.jobViewHolder> firebaseRecyclerAdapter = new
                FirebaseRecyclerAdapter<jobpost_constructor, job_posted.jobViewHolder>
                        (jobpost_constructor.class, R.layout.recycle_design_appliedpage, job_posted.jobViewHolder.class, mDatabaseReference) {
                    @Override
                    protected void populateViewHolder(jobViewHolder viewHolder, jobpost_constructor model, int position) {
                        viewHolder.setTitle(model.getDesignation());
                        viewHolder.setProfession(model.getCompany());
                        viewHolder.setJob(model.getSalary());
                        viewHolder.setJoblastdate(model.getEnddate());
                        viewHolder.setJobvacancy(model.getVacancy());
                        viewHolder.setJobdesc(model.getDescribtion());

                    }

                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_naviagtion_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()

        {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.action_home:
                        startActivity(new Intent(job_posted.this, job_posting.class));
                         Toast.makeText(job_posted.this, "home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_postjobs:
                        Toast.makeText(job_posted.this, "posted jobs", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.action_profile:
                        Toast.makeText(job_posted.this, "settings", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(job_posted.this, employer_settings.class));
                        break;


                }
                return true;
            }
        });

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

        public void setJobvacancy(String job) {
            TextView post_job = (TextView) mView.findViewById(R.id.tv_vacancy);
            post_job.setText(job);
        }

        public void setJobdesc(String job) {
            TextView post_job = (TextView) mView.findViewById(R.id.job_description);
            post_job.setText(job);
        }

        public void setJoblastdate(String job) {
            TextView post_job = (TextView) mView.findViewById(R.id.lastdate);
            post_job.setText(job);
        }

    }


}
