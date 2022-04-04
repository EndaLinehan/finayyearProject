package com.example.firebaseregistration.models;

public class DuoChromeTestResults {
    private String eyesite;
    private String date;

    public DuoChromeTestResults(String eyesite, String date) {
        this.eyesite = eyesite;
        this.date = date;
    }

    public DuoChromeTestResults() {
    }

    public String getEyesite() {
        return eyesite;
    }

    public void setEyesite(String eyesite) {
        this.eyesite = eyesite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
