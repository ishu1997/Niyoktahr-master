package com.example.hp_pc.niyoktahr.employer_fragment;

/**
 * Created by hp-pc on 8/29/2018.
 */

public class employer2_constructor {
    public String companylink;
    public String companyDescription;

    public employer2_constructor(String companylink, String companyDescription) {
        this.companylink = companylink;
        this.companyDescription = companyDescription;
    }

    public employer2_constructor() {
    }

    public String getCompanylink() {
        return companylink;
    }

    public void setCompanylink(String companylink) {
        this.companylink = companylink;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }
}
