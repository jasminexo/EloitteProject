package com.example.eloitteproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class LeaderboardActivity extends AppCompatActivity {

    //empty activity to call the score fragment bc tiff is a silly goose
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        FragmentManager fm = getSupportFragmentManager();
        ScoreFragment fragment = new ScoreFragment();
        fm.beginTransaction().add(R.id.container, fragment).commit();

    }
}
