package com.example.firebaseregistration.models;

public class VATestResults {
    private int image;
    private String answer;
    private String correctAnswer;
    private int result;
    private String date;
    private String eyesiteResult;

    public VATestResults() {
    }

    public VATestResults(int image, String answer, String correctAnswer, int result, String date, String eyesiteResult) {
        this.image = image;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
        this.result = result;
        this.date = date;
        this.eyesiteResult=eyesiteResult;
    }

    public int getImage() {
        return image;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getResult() {
        return result;
    }

    public String getDate() {
        return date;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEyesiteResult() {
        return eyesiteResult;
    }

    public void setEyesiteResult(String eyesiteResult) {
        this.eyesiteResult = eyesiteResult;
    }
}
