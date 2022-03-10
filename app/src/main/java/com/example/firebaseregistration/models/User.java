package com.example.firebaseregistration.models;

public class User {
    private String email;
    private String password;
    private CbTestResults cbTestResults;

    private int testResults;
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(CbTestResults cbTestResults) {
        this.cbTestResults = cbTestResults;
    }

    public CbTestResults getCbTestResults() {
        return cbTestResults;
    }

    public void setCbTestResults(CbTestResults cbTestResults) {
        this.cbTestResults = cbTestResults;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public int getCBTestResults() {
        return testResults;
    }

    public void setCBTestResults(int testResults) {
        this.testResults = testResults;
    }
}
