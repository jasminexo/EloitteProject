package com.example.eloitteproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CheckInQuestion.class}, version = 1)
public abstract class CheckInQuestionDatabase extends RoomDatabase {
    public abstract CheckInQuestionDao checkInQuestionDao();
}
