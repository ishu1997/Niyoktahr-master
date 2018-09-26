package com.example.hp_pc.niyoktahr;

/**
 * Created by hp-pc on 8/1/2018.
 */

public class UserProfile {
    public String  name , email , location, phoneno , dob;

    public UserProfile(String name, String location, String email, String phoneno, String dob) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.phoneno = phoneno;
        this.dob = dob;
    }

    public UserProfile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}

