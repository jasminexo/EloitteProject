package com.example.eloitteproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeacherLeaderboardActivity extends AppCompatActivity {

    //empty activity to call the teacher score fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_leaderboard);

        FragmentManager fm = getSupportFragmentManager();
        TeacherScoreFragment fragment = new TeacherScoreFragment();
        fm.beginTransaction().add(R.id.container, fragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.allStudents);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), TeacherHomeActivity.class));
                        return true;
                    case R.id.allStudents:
                        return true;
                    case R.id.appointments:
                        startActivity(new Intent(getApplicationContext(), TeacherAppointmentActivity.class));
                        return true;
                    case R.id.goals:
                        startActivity(new Intent(getApplicationContext(), TeacherGoalsActivity.class));
                        return true;
                }
                return false;
            }
        });
    }

    public void goToEmail(View view){
        Intent intent = new Intent(this, TeacherEmailActivity.class);
        startActivity(intent);
    }
}
