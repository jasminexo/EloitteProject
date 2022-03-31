package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity
public class CheckInQuestion {
    @SerializedName("qID")
    @Expose
    @PrimaryKey
    @NonNull
    public String qID;
    @SerializedName("Question")
    @Expose
    private String question;
    @SerializedName("Option1")
    @Expose
    private String option1;
    @SerializedName("Option2")
    @Expose
    private String option2;
    @SerializedName("Option3")
    @Expose
    private String option3;
    @SerializedName("Option4")
    @Expose
    private String option4;
    @SerializedName("Option5")
    @Expose
    private String option5;

    public CheckInQuestion(String qID, String question, String option1, String option2, String option3, String option4, String option5) {
        this.qID = qID;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
    }

    public static ArrayList<CheckInQuestion> getCheckInQuestionList(){
        ArrayList <CheckInQuestion> CheckInQuestionList = new ArrayList<>();
        CheckInQuestionList.add(new CheckInQuestion("q1", "How are you feeling today?", "Happy", "Neutral", "Tired", "Sad", "Frustrated"));
        CheckInQuestionList.add(new CheckInQuestion("q2", "I felt little interest in doing things.", "Strongly Disagree", "Disagree", "Neutral", "Agree", "Strongly Agree"));
        CheckInQuestionList.add(new CheckInQuestion("q3", "I felt tired or had very little energy.", "Strongly Disagree", "Disagree", "Neutral", "Agree", "Strongly Agree"));
        CheckInQuestionList.add(new CheckInQuestion("q4", "I felt bad for myself or let someone down.", "Strongly Disagree", "Disagree", "Neutral", "Agree", "Strongly Agree"));
        CheckInQuestionList.add(new CheckInQuestion("q5", "I had trouble concentrating on things.", "Strongly Disagree", "Disagree", "Neutral", "Agree", "Strongly Agree"));

        return CheckInQuestionList;
    }

    @NonNull
    public String getqID() {
        return qID;
    }

    public void setqID(@NonNull String qID) {
        this.qID = qID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }
}

