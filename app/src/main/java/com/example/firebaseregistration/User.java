package com.example.firebaseregistration;

public class User {
    private String email;
    private String password;
    private long id;
    private int testResults;
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCBTestResults() {
        return testResults;
    }

    public void setCBTestResults(int testResults) {
        this.testResults = testResults;
    }
}
