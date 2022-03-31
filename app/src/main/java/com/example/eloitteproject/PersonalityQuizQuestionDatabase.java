package com.example.eloitteproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PersonalityQuizQuestion.class}, version = 1)
public abstract class PersonalityQuizQuestionDatabase extends RoomDatabase {
    public abstract PersonalityQuizQuestionDao PersonalityQuizQuestionDao();
}
