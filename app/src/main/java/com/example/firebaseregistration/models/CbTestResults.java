package com.example.firebaseregistration.models;

import java.util.Date;

public class CbTestResults {

    private String uid;
    private int result;
    private Date date;

    public CbTestResults(int result, Date date) {

        this.result = result;
        this.date = date;
    }

    public CbTestResults() {   }


    public void setResult(int result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
