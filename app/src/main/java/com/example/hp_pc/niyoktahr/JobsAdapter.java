package com.example.hp_pc.niyoktahr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by hp-pc on 9/14/2018.
 */

public class JobsAdapter  extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<jobpost_constructor> data;

    public JobsAdapter(Context context, ArrayList<jobpost_constructor> data){
        this.mContext = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.recycleview_design, parent, false);
        return new JobsAdapter.ViewHolder(rootView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        final jobpost_constructor model = data.get(position);

        holder.setTitle(model.getDesignation());
        holder.setProfession(model.getCompany());
        holder.setJob(model.getSalary());
        holder.setJoblocation(model.getLocation());
        holder.setJobvacancy(model.getVacancy());
        holder.setJobdescription(model.getDescribtion());
        holder.setJoblastdate(model.getEnddate());

        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "applied", Toast.LENGTH_SHORT).show();


                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference("employee");

                //jobViewHolder userProfile = model ;

                myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("applied").child(model.getJobId()).setValue(model);
                ((MainActivity) mContext).allJobsData.remove(model);
                notifyDataSetChanged();


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        Button mButton;

        public ViewHolder(View itemView) {
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

        public void setJobvacancy(String job) {
            TextView post_job = (TextView) mView.findViewById(R.id.tv_job_vacancy);
            post_job.setText(job);
        }
        public void setJoblastdate(String job) {
            TextView post_job = (TextView) mView.findViewById(R.id.tv_job_lastdate);
            post_job.setText(job);
        }



    }

}
