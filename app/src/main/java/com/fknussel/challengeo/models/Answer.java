package com.fknussel.challengeo.models;

public class Answer {

    private String countryName;
    private String countryCode;
    private boolean isCorrectAnswer;

    public Answer(String countryName, String countryCode, boolean isCorrectAnswer) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(boolean isCorrectAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
    }
}
