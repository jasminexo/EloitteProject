package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executors;

public class StudentHomeActivity extends AppCompatActivity {
    int clickID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        Button logout = findViewById(R.id.btn_logout);
        //Button leaderboard = findViewById(R.id.btnLeaderboard);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

//        leaderboard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(StudentHomeActivity.this, LeaderboardActivity.class);
//                startActivity(intent);
//
//            }
//        });
    }

    public void goToCheckInActivity(View view){
        Intent intent = new Intent (this, StudentCheckInActivity.class);
        startActivity(intent);

        clickID = 1;
        update();
    }

    public void goToQuizActivity(View view){
        Intent intent = new Intent (this, StudentPersonalityQuizHomeActivity.class);
        startActivity(intent);

        clickID = 2;
        update();
    }

    public void goToDashboardActivity(View view){
        Intent intent = new Intent (this, StudentDashboardActivity.class);
        startActivity(intent);

        clickID = 3;
        update();
    }

    public void goToLeaderboardActivity(View view){
        Intent intent = new Intent (this, LeaderboardActivity.class);
        startActivity(intent);

        clickID=4;
        update();
    }

    public void goToMeditationActivity(View view){
        Intent intent = new Intent (this, StudentMeditationHomeActivity.class);
        startActivity(intent);

        clickID = 5;
        update();
    }

    public void goToStudentProfileActivity(View view){
        Intent intent = new Intent (this, StudentProfileActivity.class);
        startActivity(intent);

        clickID = 6;
        update();

    }
    //testing

    //Update the user database with points based on current logged in users
    public void update() {

        //Update user database
        UserDatabase uDB = Room.databaseBuilder(getApplicationContext(), UserDatabase.class ,
                "user-database")
                .build();

        String userID = FirebaseAuth.getInstance().getUid();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                uDB.userDao().updateScore(Click.getClick(clickID), userID);
            }
        });
    }



}