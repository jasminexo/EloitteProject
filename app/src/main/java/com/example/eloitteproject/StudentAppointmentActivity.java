package com.example.eloitteproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.hardware.camera2.params.MandatoryStreamCombination;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StudentAppointmentActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView tvMonth, bsTVMonth;
    private RecyclerView calendarRecyclerView, bsCalendarRecyclerView;
    private LocalDate selectedDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_appointment);
        initwidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initwidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        tvMonth = findViewById(R.id.tvMonth);
    }

    //Set month view from adapter
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        tvMonth.setText(monthFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++){
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add("");
            }
            else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthClicked(View view){
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthClicked(View view){
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    public void goToStudentHomeActivity(View view){
        Intent intent = new Intent (this, StudentHomeActivity.class);
        startActivity(intent);
    }

    public void goToStudentProfileActivity(View view){
        Intent intent = new Intent (this, StudentProfileActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void goToNewBookingBottomSheet(View view){
        showBookingBottomScreen();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText.equals("")){
            String message = "Selected Date" + dayText + " " + monthFromDate(selectedDate);
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        }
    }

    //shows the results in bottom screen
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showBookingBottomScreen(){

        //Instantiate new bottom sheet dialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(StudentAppointmentActivity.this);
        //Inflate the view so that users are able to view it
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.book_appointment_bsd, (LinearLayout)findViewById(R.id.llBook));

        ImageButton ibBack = bottomSheetView.findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        bsCalendarRecyclerView = bottomSheetView.findViewById(R.id.calendarRecyclerView);
        bsTVMonth = bottomSheetView.findViewById(R.id.tvMonth);

        bsTVMonth.setText(monthFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        //Set month view
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        //THIS IS NOT WORKING
        bsCalendarRecyclerView.setLayoutManager(layoutManager);
        bsCalendarRecyclerView.setAdapter(calendarAdapter);

        ImageButton ibPreviousMonth = bottomSheetView.findViewById(R.id.ibPreviousMonth);
        ibPreviousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate = selectedDate.minusMonths(1);
                bsTVMonth.setText(monthFromDate(selectedDate));
                ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
            }
        });

        ImageButton ibNextMonth = bottomSheetView.findViewById(R.id.ibNextMonth);
        ibNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate = selectedDate.plusMonths(1);
                bsTVMonth.setText(monthFromDate(selectedDate));
                ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
            }
        });

        //DELETE THIS LATER
        //returns to student home screen
        Button btnReturnToHome = bottomSheetView.findViewById(R.id.btnReturntoHome);
        btnReturnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentAppointmentActivity.this, StudentHomeActivity.class);
                startActivity(intent);
                bottomSheetDialog.dismiss();
            }
        });

        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
    }
}