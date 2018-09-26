package com.example.hp_pc.niyoktahr;

/**
 * Created by hp-pc on 9/2/2018.
 */

public class jobapplied_constructor {
    public String jobTitlee;
    public String companyName;
    public String location;

    public jobapplied_constructor(String jobTitlee, String companyName, String location) {
        this.jobTitlee = jobTitlee;
        this.companyName = companyName;
        this.location = location;
    }

    public jobapplied_constructor() {
    }

    public String getJobTitlee() {
        return jobTitlee;
    }

    public void setJobTitlee(String jobTitlee) {
        this.jobTitlee = jobTitlee;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}