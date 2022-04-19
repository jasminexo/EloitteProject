package com.example.eloitteproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class TeacherLeaderboardActivity extends AppCompatActivity {

    //empty activity to call the teacher score fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        FragmentManager fm = getSupportFragmentManager();
        TeacherScoreFragment fragment = new TeacherScoreFragment();
        fm.beginTransaction().add(R.id.container, fragment).commit();

    }
}
