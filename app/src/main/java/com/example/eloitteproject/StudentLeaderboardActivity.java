package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentLeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_leaderboard);
    }

    public void goToStudentHomeActivity(View view){
        Intent intent = new Intent (this, StudentHomeActivity.class);
        startActivity(intent);
    }
}