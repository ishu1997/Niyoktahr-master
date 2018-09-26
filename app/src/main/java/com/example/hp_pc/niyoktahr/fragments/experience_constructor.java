package com.example.hp_pc.niyoktahr.fragments;

/**
 * Created by hp-pc on 8/19/2018.
 */

public class experience_constructor {
    public String   year , month , description_job,experience;

    public experience_constructor() {
    }

    public experience_constructor(String year, String month, String description_job) {
        this.year = year;
        this.month = month;
        this.description_job = description_job;
    }

    public experience_constructor(String year, String month, String description_job, String experience) {
        this.year = year;
        this.month = month;
        this.description_job = description_job;
        this.experience = experience;
    }

    public experience_constructor(String experience) {
        this.experience = experience;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDescription_job() {
        return description_job;
    }

    public void setDescription_job(String description_job) {
        this.description_job = description_job;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
