package com.example.hp_pc.niyoktahr;

/**
 * Created by hp-pc on 8/29/2018.
 */

public class jobpost_constructor{
    public String designation,company,vacancy,salary,describtion,job_post_email,location,enddate;

    public String userid, jobId;


    public jobpost_constructor() {
    }

    public jobpost_constructor(String designation, String company, String vacancy, String salary, String describtion, String job_post_email, String location, String enddate, String userid) {
        this.designation = designation;
        this.company = company;
        this.vacancy = vacancy;
        this.salary = salary;
        this.describtion = describtion;
        this.job_post_email = job_post_email;
        this.location = location;
        this.enddate = enddate;
        this.userid = userid;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getJob_post_email() {
        return job_post_email;
    }

    public void setJob_post_email(String job_post_email) {
        this.job_post_email = job_post_email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
