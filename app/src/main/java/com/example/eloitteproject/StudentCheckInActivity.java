package com.example.eloitteproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StudentCheckInActivity extends AppCompatActivity {

    private TextView tvQuestionNumber, tvQuestion, tvTime, tvLabels1, tvLabels2, tvLabels3;
    private ImageView ivImage;
    private Button btnNext, btnReturnHome;
    private SeekBar seekBar;
    private String qID, countDownTime;
    CheckInQuestionDatabase checkinDB;
    boolean quizCompleted;
    int currentScore = 0, currentQuestionPosition, seekBarProgress = 3;
    int q1Score, q2Score, q3Score, q4Score, q5Score, dayClicked = 1;
    long duration;
    ZonedDateTime timeNow;
    LocalDate tomorrow;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_checkin);

        tvLabels1 = findViewById(R.id.tvLabels1);
        tvLabels2 = findViewById(R.id.tvLabels2);
        tvLabels3 = findViewById(R.id.tvLabels3);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvTime = findViewById(R.id.tvTime);
        ivImage = findViewById(R.id.ivImage);
        btnNext = findViewById(R.id.btnNext);
        btnReturnHome = findViewById(R.id.btnReturnHome);
        seekBar = findViewById(R.id.seekBar);

        Intent intent = getIntent();
        String StrCurrentQuestionPosition = intent.getStringExtra("receiveCurrentQPosition");
        try {
            currentQuestionPosition = Integer.parseInt(StrCurrentQuestionPosition);
        }
        catch (NumberFormatException e) {
            currentQuestionPosition = 1;
        }


        //Database
        checkinDB = Room.databaseBuilder(getApplicationContext(), CheckInQuestionDatabase.class ,
                "check-in-question-database")
                .build();

        quizCompleted = false;

        seekBarChanged();
        displayCheckInQuestion();
        nextButtonClicked();

    }

    //when user changes the seekbar
    //Source: https://www.homeandlearn.co.uk/android/android_seekbar.html
    private void seekBarChanged (){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //set int value as corresponding progress
                seekBarProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void displayCheckInQuestion() {
        if (currentQuestionPosition >= 1 && currentQuestionPosition <= 5) {
            quizCompleted = false;

            tvTime.setVisibility(View.GONE);
            btnNext.setVisibility(View.VISIBLE);
            btnReturnHome.setVisibility(View.GONE);
            seekBar.setVisibility(View.VISIBLE);
            tvLabels1.setVisibility(View.VISIBLE);
            tvLabels2.setVisibility(View.VISIBLE);
            tvLabels3.setVisibility(View.VISIBLE);

            //Assign a question from the bank
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    for(CheckInQuestion q : CheckInQuestion.getCheckInQuestionList()){
                        checkinDB.checkInQuestionDao().insert(q);
                    }
                    qID = "q"+ currentQuestionPosition;
                    CheckInQuestion desiredQuestion = checkinDB.checkInQuestionDao().getCheckInQuestion(qID);
                    String checkInQuestion = desiredQuestion.getQuestion();
                    Integer checkInImage = desiredQuestion.getImage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvQuestionNumber.setText(currentQuestionPosition+"/5");
                            tvQuestion.setText(checkInQuestion);
                            ivImage.setImageResource(checkInImage);
                            nextButtonClicked();
                        }
                    });
                }
            });
        } else if (currentQuestionPosition == 6){
            quizCompleted = true;
            finishedQuiz();
        }
    }

    private void nextButtonClicked() {
        //saving the score for each question - need to find a way to upload into database with date and time
        if (qID == "q1"){
            q1Score = seekBarProgress;
        } else if (qID == "q2"){
            q2Score = seekBarProgress;
        } else if (qID == "q3"){
            q3Score = seekBarProgress;
        } else if (qID == "q4"){
            q4Score = seekBarProgress;
        } else if (qID == "q5"){
            q5Score = seekBarProgress;
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                currentQuestionPosition++;
                displayCheckInQuestion();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void finishedQuiz() {
        btnNext.setVisibility(View.GONE);
        btnReturnHome.setVisibility(View.VISIBLE);
        tvTime.setVisibility(View.VISIBLE);
        seekBar.setVisibility(View.INVISIBLE);
        tvLabels1.setVisibility(View.INVISIBLE);
        tvLabels2.setVisibility(View.INVISIBLE);
        tvLabels3.setVisibility(View.INVISIBLE);

        //work out duration
        ZoneId zoneID = ZoneId.of("Australia/Sydney");
        timeNow = ZonedDateTime.now(zoneID);
        tomorrow = timeNow.toLocalDate().plusDays(1);
        ZonedDateTime tomorrowStart = tomorrow.atStartOfDay(zoneID);
        duration = Duration.between(timeNow,tomorrowStart).toMillis();

        //countdown timer
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //when tick, convert millisecond to hours, min, sec
                countDownTime = String.format(Locale.ENGLISH,"%02d:%02d:%02d"
                        , TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                tvTime.setText(countDownTime);

                tvQuestionNumber.setText("5/5");
                tvQuestion.setText("\n\nCheck-In Complete!\nCome back tomorrow:");
                ivImage.setImageResource(R.drawable.clock);
            };

            @Override
            public void onFinish() {
                quizCompleted = false;
                currentQuestionPosition = 1;
                countDownTime = "00:00:00";
                //Assign a question from the bank
                displayCheckInQuestion();
            }
        }.start();
    }

    public void goToStudentHomeActivity(View view){
//        lastClickTime = SystemClock.elapsedRealtime(); //returns in millisecond
//        lastClickTime = timeNow;
//        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
//        int lastClickTime = settings.getInt("last_click_time", -1);
        Calendar calendar = Calendar.getInstance();
        dayClicked = calendar.get(Calendar.DAY_OF_YEAR);

        Intent intent = new Intent (this, StudentHomeActivity.class);
//        intent.putExtra("receiveLastClickDay", lastClickTime);
        Bundle extras = new Bundle();
        extras.putInt("receiveLastClickDay", dayClicked);
        extras.putString("from_activity", "studentCheckInActivity");
        intent.putExtras(extras);
        startActivity(intent);
////
//        SharedPreferences.Editor editor = settings.edit();
//        editor.putInt("last_click_time", dayClicked);
//        editor.commit();

//        long x;
//        x = dayClicked;
    }

    public void goToStudentProfileActivity(View view){
        Intent intent = new Intent (this, StudentProfileActivity.class);
        startActivity(intent);
    }
}