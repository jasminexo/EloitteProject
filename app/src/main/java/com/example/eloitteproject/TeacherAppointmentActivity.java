package com.example.eloitteproject;

import static com.example.eloitteproject.CalendarUtils.dayFromDate;
import static com.example.eloitteproject.CalendarUtils.daysInMonthArray;
import static com.example.eloitteproject.CalendarUtils.monthFromDate;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TeacherAppointmentActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{
    private TextView tvMonth;
    private RecyclerView bsCalendarRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_appointment);

        initwidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();

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

    private void initwidgets() {
        bsCalendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        tvMonth = findViewById(R.id.tvMonth);
    }

    //Set month view from adapter for bottom sheet
    public void setMonthView() {
        tvMonth.setText(monthFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 7);
        bsCalendarRecyclerView.setLayoutManager(layoutManager);
        bsCalendarRecyclerView.setAdapter(calendarAdapter);
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        if (date != null) {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }
}