package com.example.eloitteproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertOneUser (User user);

    @Insert
    void insertAll(List<User> users);

    @Query("SELECT * FROM User WHERE uID =:uID")
    User getOneUserByUserName (String uID);

    //Update score
    @Query("UPDATE User SET score = score + :addNum WHERE uID = :uID")
    void updateScore (int addNum, String uID);

    //Update profile details

    //Update Name
    @Query("UPDATE User SET fullName = :addName WHERE uID = :uID")
    void updateName(String addName, String uID);

    //Get Email
    @Query("UPDATE User SET email = :addEmail WHERE uID =:uID")
    void updateEmail(String addEmail, String uID);

    //Update DOB
    @Query("UPDATE User SET DOB = :addDOB WHERE uID =:uID")
    void updateDOB(String addDOB, String uID);

    //Update Parent Email
    @Query("UPDATE User SET parentEmail = :addParentEmail WHERE uID =:uID")
    void updateParentEmail(String addParentEmail, String uID);

//    //Update Profile Pic
//    @Query("UPDATE User SET profilePic = ? WHERE uID =:uID")
//    void updateProfilePic(String uID);
//
//    //Update ProfileBG
//    @Query("UPDATE User SET profilePicBG = ? WHERE uID =:uID")
//    void updateProfileBG(String uID);



    //Get score
    @Query("SELECT score FROM User WHERE uID =:uID")
    int getScore(String uID);

    //Get Name
    @Query("SELECT fullName FROM User WHERE uID =:uID")
    String getName(String uID);

    //Get Email
    @Query("SELECT email FROM User WHERE uID =:uID")
    String getEmail(String uID);

    //Get DOB
    @Query("SELECT DOB FROM User WHERE uID =:uID")
    String getDOB(String uID);

    //Get Parent Email
    @Query("SELECT parentEmail FROM User WHERE uID =:uID")
    String getParentEmail(String uID);

    //Get Profile Pic
    @Query("SELECT profilePic FROM User WHERE uID =:uID")
    int getProfilePic(String uID);

    //Get ProfileBG
    @Query("SELECT profilePicBG FROM User WHERE uID =:uID")
    String getProfileBG(String uID);

    //Get all user data
    @Query("SELECT * FROM User")
    List <User> getAllUsers();

    //Get count of user records
    @Query("SELECT COUNT(uID) from User")
    int getUserCount();
}

