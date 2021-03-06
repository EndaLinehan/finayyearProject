package com.example.firebaseregistration.models;

import java.util.Date;

public class CbTestResults {

    private String uid;
    private int plate;
    private int answer;
    private int correctAnswer;
    private int result;
    private String date;

    public CbTestResults(int plate, int answer, int correctAnswer, int result, String date) {
        this.plate = plate;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
        this.result = result;
        this.date = date;
    }

    public CbTestResults() {   }

    public int getPlate() {
        return plate;
    }

    public void setPlate(int plate) {
        this.plate = plate;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CbTestResults(int plate, int answer, int correctAnswer, int result) {
        this.plate = plate;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
        this.result = result;
    }
}
