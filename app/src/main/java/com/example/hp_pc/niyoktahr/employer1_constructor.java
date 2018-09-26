package com.example.hp_pc.niyoktahr;

/**
 * Created by hp-pc on 8/24/2018.
 */

public class employer1_constructor {
    public String employer_form_name, employer_form_post, company_employer_name, company_employer_website, contact_employer_number, contact_employer_email, employer_location;



    public employer1_constructor() {
    }

    public employer1_constructor(String employer_form_name, String employer_form_post, String company_employer_name, String company_employer_website, String contact_employer_number, String contact_employer_email, String employer_location) {
        this.employer_form_name = employer_form_name;
        this.employer_form_post = employer_form_post;
        this.company_employer_name = company_employer_name;
        this.company_employer_website = company_employer_website;
        this.contact_employer_number = contact_employer_number;
        this.contact_employer_email = contact_employer_email;
        this.employer_location = employer_location;
    }

    public String getEmployer_form_name() {
        return employer_form_name;
    }

    public void setEmployer_form_name(String employer_form_name) {
        this.employer_form_name = employer_form_name;
    }

    public String getEmployer_form_post() {
        return employer_form_post;
    }

    public void setEmployer_form_post(String employer_form_post) {
        this.employer_form_post = employer_form_post;
    }

    public String getCompany_employer_name() {
        return company_employer_name;
    }

    public void setCompany_employer_name(String company_employer_name) {
        this.company_employer_name = company_employer_name;
    }

    public String getCompany_employer_website() {
        return company_employer_website;
    }

    public void setCompany_employer_website(String company_employer_website) {
        this.company_employer_website = company_employer_website;
    }

    public String getContact_employer_number() {
        return contact_employer_number;
    }

    public void setContact_employer_number(String contact_employer_number) {
        this.contact_employer_number = contact_employer_number;
    }

    public String getContact_employer_email() {
        return contact_employer_email;
    }

    public void setContact_employer_email(String contact_employer_email) {
        this.contact_employer_email = contact_employer_email;
    }

    public String getEmployer_location() {
        return employer_location;
    }

    public void setEmployer_location(String employer_location) {
        this.employer_location = employer_location;
    }
}
