package com.fknussel.challengeo.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {

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

    protected Answer(Parcel in) {
        countryName = in.readString();
        countryCode = in.readString();
        isCorrectAnswer = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(countryName);
        dest.writeString(countryCode);
        dest.writeByte((byte) (isCorrectAnswer ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}