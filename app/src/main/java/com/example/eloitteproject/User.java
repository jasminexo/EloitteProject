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
    private Integer score;
    @SerializedName("Date of Birth")
    @Expose
    private String DOB;
    @SerializedName("Parent Email")
    @Expose
    private String parentEmail;
    @SerializedName("Profile Pic")
    @Expose
    private int profilePic;
    @SerializedName("Profile Pic BG")
    @Expose
    private String profilePicBG;

    //Constructor
    public User(@NonNull String uID, String fullName, String password, String email, Integer score, String DOB, String parentEmail, int profilePic, String profilePicBG) {
        this.uID = uID;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.score = score;
        this.DOB = DOB;
        this.parentEmail = parentEmail;
        this.profilePic = profilePic;
        this.profilePicBG = profilePicBG;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePicBG() {
        return profilePicBG;
    }

    public void setProfilePicBG(String profilePicBG) {
        this.profilePicBG = profilePicBG;
    }

    public static ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();

        userList.add(new User("vemLFL7Ec3S8kkccuXc2uEfgDTp1", "Ken Hayes",
                "123456", "kenken@student.edu.au", 24, "01/04/22",
                "mailinh.dinhh@gmail.com", R.drawable.hedgehog, "circle_beige"));
        userList.add(new User("RQX1UeReoIQHiLk2zYO4eUZyYye2", "Mikaela Vo",
                "123456", "micklepickles@student.edu.au", 12, "01/11/21",
                "votiffany6@gmail.com", R.drawable.bear, "circle_purple"));
        userList.add(new User("QuHZsscwHQVQVgukbZOjcrEDuj12", "Milo Lim",
                "123456", "badussy@student.edu.au", 10, "19/12/21",
                "jasmine.lim1177@gmail.com", R.drawable.fox_two, "circle_blue"));
        userList.add(new User("vemsjfhdjsS8kkccuXc2uEfgDTp1", "Sammy Hayes",
                "123456", "sammy@student.edu.au", 8, "01/04/22",
                "mailinh.dinhh@gmail.com", R.drawable.bunny, "circle_brown"));
        userList.add(new User("RQX1Uskjdgnshfk2zYO4eUZyYye2", "Scotty Vo",
                "123456", "scotty@student.edu.au", 6, "01/11/21",
                "votiffany6@gmail.com", R.drawable.bear, "circle_blue"));
        userList.add(new User("sng7689hHGHDHHSAJDJHSJsjfjfn", "Rikki Dinh",
                "123456", "rikki@student.edu.au", 5, "19/12/21",
                "mailinh.dinhh@gmail.com", R.drawable.owl, "circle_yellow"));
        userList.add(new User("QuHuhefahsdjbfiaasOjcrEDuj12", "Max Hayes",
                "123456", "max@student.edu.au", 3, "19/12/21",
                "mailinh.dinhh@gmail.com", R.drawable.penguin, "circle_purple"));
        userList.add(new User("QuHZsscskdfgnjsnfaOjcrEDuj12", "Tiger Hayes",
                "123456", "tiger@student.edu.au", 1, "19/12/21",
                "mailinh.dinhh@gmail.com", R.drawable.penguin, "circle_orange"));
        userList.add(new User("RQX1UeRjsdnfjszYO4eUZyffYye2", "Michael Vo",
                "123456", "michaelthebudgie@student.edu.au", 0, "01/11/21",
                "votiffany6@gmail.com", R.drawable.bunny, "circle_blue"));
        return userList;
    }

}

