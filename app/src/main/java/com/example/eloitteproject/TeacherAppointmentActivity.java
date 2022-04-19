package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeacherAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_appointment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.appointments);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), TeacherHomeActivity.class));
                        return true;
                    case R.id.allStudents:
                        startActivity(new Intent(getApplicationContext(), LeaderboardActivity.class));
                        return true;
                    case R.id.appointments:
                        return true;
                    case R.id.goals:
                        startActivity(new Intent(getApplicationContext(), TeacherGoalsActivity.class));
                        return true;
                }
                return false;
            }
        });
    }
}