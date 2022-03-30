package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class StudentHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        Button logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }

    public void goToCheckInActivity(View view){
        Intent intent = new Intent (this, StudentCheckInActivity.class);
        startActivity(intent);
    }

    public void goToQuizActivity(View view){
        Intent intent = new Intent (this, StudentQuizHomeActivity.class);
        startActivity(intent);
    }

    public void goToDashboardActivity(View view){
        Intent intent = new Intent (this, StudentDashboardActivity.class);
        startActivity(intent);
    }

    public void goToLeaderboardActivity(View view){
        Intent intent = new Intent (this, ScoreFragment.class);
        startActivity(intent);
    }

    public void goToMeditationActivity(View view){
        Intent intent = new Intent (this, StudentMeditationActivity.class);
        startActivity(intent);
    }

    public void goToStudentProfileActivity(View view){
        Intent intent = new Intent (this, StudentProfileActivity.class);
        startActivity(intent);
    }
    //testing
}