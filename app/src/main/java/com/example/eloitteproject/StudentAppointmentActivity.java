package com.example.eloitteproject;

import static com.example.eloitteproject.CalendarUtils.daysInMonthArray;
import static com.example.eloitteproject.CalendarUtils.daysInWeekArray;
import static com.example.eloitteproject.CalendarUtils.monthFromDate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.time.LocalDate;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class StudentAppointmentActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView tvMonth, bsTVMonth;
    private RecyclerView calendarRecyclerView, bsCalendarRecyclerView;
//    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_appointment);
        initwidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setWeekView();
    }

    private void initwidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        tvMonth = findViewById(R.id.tvMonth);
    }

    //Set week view from adapter for main activity
    public void setWeekView() {
        tvMonth.setText(monthFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    //Set month view from adapter for bottom sheet
    public void setMonthView() {
        bsTVMonth.setText(monthFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 7);
        bsCalendarRecyclerView.setLayoutManager(layoutManager);
        bsCalendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousWeekClicked(View view){
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekClicked(View view){
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    public void goToStudentHomeActivity(View view){
        Intent intent = new Intent (this, StudentHomeActivity.class);
        startActivity(intent);
    }

    public void goToStudentProfileActivity(View view){
        Intent intent = new Intent (this, StudentProfileActivity.class);
        startActivity(intent);
    }

    public void goToNewBookingBottomSheet(View view){
        showBookingBottomScreen();
    }

    //on main activity, when user clicks on day of the week, it is highlighted
    //we want to apply this for monthly view not weekly
    @Override
    public void onItemClick(int position, LocalDate date) {
        if (date != null) {
            CalendarUtils.selectedDate = date;
            setWeekView();
        }
    }

    //shows the results in bottom screen
    private void showBookingBottomScreen(){

        //Instantiate new bottom sheet dialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(StudentAppointmentActivity.this);
        //Inflate the view so that users are able to view it
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.book_appointment_bsd, (LinearLayout)findViewById(R.id.llBook));

        //Hide Bottom sheet view when back button clicked
        ImageButton ibBack = bottomSheetView.findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        bsCalendarRecyclerView = bottomSheetView.findViewById(R.id.calendarRecyclerView);
        bsTVMonth = bottomSheetView.findViewById(R.id.tvMonth);

        setMonthView();

        ImageButton ibPreviousMonth = bottomSheetView.findViewById(R.id.ibPreviousMonth);
        ibPreviousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
                bsTVMonth.setText(monthFromDate(CalendarUtils.selectedDate));
                setMonthView();
            }
        });

        ImageButton ibNextMonth = bottomSheetView.findViewById(R.id.ibNextMonth);
        ibNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
                bsTVMonth.setText(monthFromDate(CalendarUtils.selectedDate));
                setMonthView();
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