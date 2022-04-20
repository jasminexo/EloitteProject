package com.example.eloitteproject;

import static com.example.eloitteproject.CalendarUtils.dayFromDate;
import static com.example.eloitteproject.CalendarUtils.daysInMonthArray;
import static com.example.eloitteproject.CalendarUtils.daysInWeekArray;
import static com.example.eloitteproject.CalendarUtils.monthFromDate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.time.LocalDate;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class StudentAppointmentActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener, View.OnClickListener {

    private TextView tvMonth, bsTVMonth, bsTVDate, tvTeacherName, tvDate, tvTime, tvLocation;
    private CheckBox cbDate;
    private RecyclerView calendarRecyclerView, bsCalendarRecyclerView;
    public String time;
    private Button btn830, btn845, btn900, btn300, btn315, btn330, btnBookAppointment;
    boolean bsClicked;

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
        tvTeacherName = findViewById(R.id.tvTeacherName);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        tvLocation = findViewById(R.id.tvLocation);
    }

    public void setAppointmentView() {
        tvTeacherName.setText("Mr. John Smith");
        tvDate.setText(dayFromDate(CalendarUtils.selectedDate));
        tvTime.setText(time);
        tvLocation.setText("face-to-face");
    }

    //Set week view from adapter for main activity
    public void setWeekView() {
        tvMonth.setText(monthFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    //Set month view from adapter for bottom sheet
    public void setMonthView() {
        bsTVMonth.setText(monthFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 7);
        bsCalendarRecyclerView.setLayoutManager(layoutManager);
        bsCalendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousWeekClicked(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekClicked(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    public void goToStudentHomeActivity(View view) {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }

    public void goToStudentProfileActivity(View view) {
        Intent intent = new Intent(this, StudentProfileActivity.class);
        startActivity(intent);
    }

    public void goToNewBookingBottomSheet(View view) {
        showBookingBottomScreen();
    }

    //on main activity, when user clicks on day of the week, it is highlighted
    //we want to apply this for monthly view not weekly
    @Override
    public void onItemClick(int position, LocalDate date) {
        if (date != null) {
            CalendarUtils.selectedDate = date;
            setWeekView();
            if (bsClicked == true){
                setMonthView();
                bsTVDate.setText(dayFromDate(CalendarUtils.selectedDate));
            }
        }
    }

    //shows the results in bottom screen
    public void showBookingBottomScreen() {
        bsClicked = true;
        //Instantiate new bottom sheet dialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(StudentAppointmentActivity.this);
        //Inflate the view so that users are able to view it
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.book_appointment_bsd, (LinearLayout) findViewById(R.id.llBook));

        //Hide Bottom sheet view when back button clicked
        ImageButton ibBack = bottomSheetView.findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                setWeekView();
            }
        });

        bsCalendarRecyclerView = bottomSheetView.findViewById(R.id.calendarRecyclerView);
        bsTVMonth = bottomSheetView.findViewById(R.id.tvMonth);
        bsTVDate = bottomSheetView.findViewById(R.id.tvDDMonthYYYY);
        cbDate = bottomSheetView.findViewById(R.id.cbDate);
        btn830 = bottomSheetView.findViewById(R.id.btn830);
        btn845 = bottomSheetView.findViewById(R.id.btn845);
        btn900 = bottomSheetView.findViewById(R.id.btn900);
        btn300 = bottomSheetView.findViewById(R.id.btn300);
        btn315 = bottomSheetView.findViewById(R.id.btn315);
        btn330 = bottomSheetView.findViewById(R.id.btn330);
        btnBookAppointment = bottomSheetView.findViewById(R.id.btnBookAppointment);

        btn830.setOnClickListener(this);
        btn845.setOnClickListener(this);
        btn900.setOnClickListener(this);
        btn300.setOnClickListener(this);
        btn315.setOnClickListener(this);
        btn330.setOnClickListener(this);

        setMonthView();
        bsTVDate.setText(dayFromDate(CalendarUtils.selectedDate));

        ImageButton ibPreviousDay = bottomSheetView.findViewById(R.id.ibPreviousDay);
        ibPreviousDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusDays(1);
                bsTVMonth.setText(monthFromDate(CalendarUtils.selectedDate));
                bsTVDate.setText(dayFromDate(CalendarUtils.selectedDate));
                setMonthView();
            }
        });

        ImageButton ibNextDay = bottomSheetView.findViewById(R.id.ibNextDay);
        ibNextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusDays(1);
                bsTVMonth.setText(monthFromDate(CalendarUtils.selectedDate));
                bsTVDate.setText(dayFromDate(CalendarUtils.selectedDate));
                setMonthView();
            }
        });

        btnBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbDate.isChecked()) {
                    bottomSheetDialog.dismiss();
                    setWeekView();
                    setAppointmentView();
                } else {
                    cbDate.setBackgroundResource(R.color.red);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cbDate.setBackgroundResource(R.color.transparent); //set the color to transparent
                        }
                    }, 300);
                }
            }
        });

        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
        setWeekView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn830:
                btn830.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_brown));
                btn845.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn900.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn300.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn315.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn330.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                time = "8:30am";
                break;
            case R.id.btn845:
                btn830.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn845.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_brown));
                btn900.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn300.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn315.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn330.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                time = "8:45am";
                break;
            case R.id.btn900:
                btn830.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn845.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn900.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_brown));
                btn300.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn315.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn330.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                time = "9:00am";
                break;
            case R.id.btn300:
                btn830.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn845.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn900.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn300.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_brown));
                btn315.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn330.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                time = "3:00pm";
                break;
            case R.id.btn315:
                btn830.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn845.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn900.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn300.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn315.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_brown));
                btn330.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                time = "3:15pm";
                break;
            case R.id.btn330:
                btn830.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn845.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn900.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn300.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn315.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_light));
                btn330.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.light_brown));
                time = "3:30pm";
                break;
        }
        cbDate.setText(dayFromDate(CalendarUtils.selectedDate) + " at " + time);
    }

}