package com.example.hp_pc.niyoktahr.fragments;

/**
 * Created by hp-pc on 8/18/2018.
 */

public class Education_constructor {
   public String qualification , language , skills , passing_year ,college;

    public Education_constructor(String qualification, String language, String skills, String passing_year, String college) {
        this.qualification = qualification;
        this.language = language;
        this.skills = skills;
        this.passing_year = passing_year;
        this.college = college;
    }

    public Education_constructor() {
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPassing_year() {
        return passing_year;
    }

    public void setPassing_year(String passing_year) {
        this.passing_year = passing_year;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
