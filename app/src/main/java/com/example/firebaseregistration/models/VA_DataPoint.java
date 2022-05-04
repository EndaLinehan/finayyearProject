package com.example.firebaseregistration.models;

public class VA_DataPoint {
    private String xValue;
    private String yValue;

    public VA_DataPoint(String xValue, String yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public VA_DataPoint() {
    }

    public String getxValue() {
        return xValue;
    }

    public void setxValue(String xValue) {
        this.xValue = xValue;
    }

    public String getyValue() {
        return yValue;
    }

    public void setyValue(String yValue) {
        this.yValue = yValue;
    }
}
