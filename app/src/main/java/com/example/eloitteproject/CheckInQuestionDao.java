package com.example.eloitteproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CheckInQuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (CheckInQuestion...checkInQuestion);

    @Query("SELECT * FROM checkInQuestion WHERE qID =:qID")
    CheckInQuestion getCheckInQuestion(String qID);

    //Count number of questions in the database
    @Query("SELECT COUNT(qID) FROM checkinquestion")
    int getCheckInQuestionCount();

    @Query("SELECT * FROM checkinquestion")
    CheckInQuestion  getAllQuestions();
}
