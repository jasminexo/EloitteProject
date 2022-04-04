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
        userList.add(new User("V5fbEq1O65TJnoPVnd4VjnrOBtK2", "Rikki Dinh", "111111", "rikki@student.edu.au" , 69));
        userList.add(new User("6xLDbp1JOFgsBXtuXnjJxo3U5WG3", "Mickle Pickles", "123456", "micklepickles@student.edu.au"  , 420));
        userList.add(new User("yxsL0jMN4scp8ytEMQwOJdoGtxs2", "Milo Lim", "123456", "milo@student.edu.au"  , 420));
        return userList;
    }

}

