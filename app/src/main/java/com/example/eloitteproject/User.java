package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity
public class User {
    @SerializedName("uID")
    @Expose
    @NonNull
    @PrimaryKey
    private String uID;
    @SerializedName("Full Name")
    @Expose
    private String fullName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Score")
    @Expose
    private int score;



    //existing user constructor
    public User(@NonNull String uID, String fullName, String password, String email, int score) {
        this.uID = uID;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.score = score;
    }


    @NonNull
    public String getUID() {
        return uID;
    }

    public void setUID(@NonNull String uID) {
        this.uID = uID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static ArrayList <User> getUserList(){
        ArrayList <User> userList = new ArrayList<>();
        userList.add(new User("9jAOtjCTl6ex3SB0o9R7IZubomc2", "Justin", "asdasd1", "3200" , 6969));
        userList.add(new User("IJHt21Woi8VyjhAnDkVqaMlvPM52", "Tiffany", "asdasd1", "2000"  , 420420));
        return userList;
    }

}

