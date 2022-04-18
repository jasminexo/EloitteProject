package com.example.eloitteproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PersonalityQuizQuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PersonalityQuizQuestion... personalityQuizQuestion);

    @Query("SELECT * FROM personalityQuizQuestion WHERE qID =:qID")
    PersonalityQuizQuestion getPersonalityQuizQuestion(String qID);

    //Count number of questions in the database
    @Query("SELECT COUNT(qID) FROM personalityquizquestion")
    int getPersonalityQuizQuestionCount();

    @Query("SELECT * FROM personalityquizquestion")
    PersonalityQuizQuestion getAllQuestions();
}
