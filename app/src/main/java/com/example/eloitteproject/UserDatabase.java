package com.example.eloitteproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 2, exportSchema = true)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
