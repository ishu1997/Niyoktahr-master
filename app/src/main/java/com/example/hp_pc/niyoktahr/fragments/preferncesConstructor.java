package com.example.hp_pc.niyoktahr.fragments;

/**
 * Created by hp-pc on 9/16/2018.
 */

public class preferncesConstructor {
 public String   spinlocation ,spinjob_1 ,spinjob_2 ,spinjob_3 ;

    public preferncesConstructor(String spinlocation, String spinjob_1, String spinjob_2, String spinjob_3) {
        this.spinlocation = spinlocation;
        this.spinjob_1 = spinjob_1;
        this.spinjob_2 = spinjob_2;
        this.spinjob_3 = spinjob_3;
    }

    public preferncesConstructor(String spinlocation, String spinjob_1) {
        this.spinlocation = spinlocation;
        this.spinjob_1 = spinjob_1;
    }

    public preferncesConstructor() {
    }

    public preferncesConstructor(String spinjob_1) {
        this.spinjob_1 = spinjob_1;
    }

    public String getSpinlocation() {
        return spinlocation;
    }

    public void setSpinlocation(String spinlocation) {
        this.spinlocation = spinlocation;
    }

    public String getSpinjob_1() {
        return spinjob_1;
    }

    public void setSpinjob_1(String spinjob_1) {
        this.spinjob_1 = spinjob_1;
    }

    public String getSpinjob_2() {
        return spinjob_2;
    }

    public void setSpinjob_2(String spinjob_2) {
        this.spinjob_2 = spinjob_2;
    }

    public String getSpinjob_3() {
        return spinjob_3;
    }

    public void setSpinjob_3(String spinjob_3) {
        this.spinjob_3 = spinjob_3;
    }
}
