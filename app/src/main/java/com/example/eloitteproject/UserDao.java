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

    //Get score
    @Query("SELECT score FROM User WHERE uID =:uID")
    int getScore(String uID);

    //Get Name
    @Query("SELECT fullName FROM User WHERE uID =:uID")
    String getName(String uID);

    //Get all user data
    @Query("SELECT * FROM User")
    List <User> getAllUsers();

    //Get count of user records
    @Query("SELECT COUNT(uID) from User")
    int getUserCount();
}

