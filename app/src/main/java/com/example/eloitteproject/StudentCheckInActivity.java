package com.example.eloitteproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class StudentCheckInActivity extends AppCompatActivity {

    private TextView tvQuestionNumber, tvQuestion, tvHours, tvMinutes, tvSeconds;
    private ImageView ivImage;
    private Button btnNext, btnReturnHome;
    private SeekBar seekBar;
    private String qID;
    CheckInQuestionDatabase qDB;
    int currentScore = 0, currentQuestionPosition = 1, seekBarProgress = 3, currentArrayPosition = 0;
    int q1Score, q2Score, q3Score, q4Score, q5Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_checkin);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvHours = findViewById(R.id.tvHours);
        tvMinutes = findViewById(R.id.tvMinutes);
        tvSeconds = findViewById(R.id.tvSeconds);
        ivImage = findViewById(R.id.ivImage);
        btnNext = findViewById(R.id.btnNext);
        seekBar = findViewById(R.id.seekBar);

        //Database
        qDB = Room.databaseBuilder(getApplicationContext(), CheckInQuestionDatabase.class ,
                "check-in-question-database")
                .build();

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

    private void displayCheckInQuestion() {
        //Assign a question from the bank
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                for(CheckInQuestion q : CheckInQuestion.getCheckInQuestionList()){
                        qDB.checkInQuestionDao().insert(q);
                }
                qID = "q"+ currentQuestionPosition;
                CheckInQuestion desiredQuestion = qDB.checkInQuestionDao().getCheckInQuestion(qID);
                String checkInQuestion = desiredQuestion.getQuestion();
                Integer checkInImage = desiredQuestion.getImage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvQuestionNumber.setText(currentQuestionPosition+"/5");
                        tvQuestion.setText(checkInQuestion);
                        ivImage.setImageResource(checkInImage);
                    }
                });
            }
        });
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
                if (currentQuestionPosition == 5){
                    btnNext.setVisibility(View.GONE);
                    btnReturnHome.setVisibility(View.VISIBLE);
                    tvHours.setVisibility(View.VISIBLE);
                    tvMinutes.setVisibility(View.VISIBLE);
                    tvSeconds.setVisibility(View.VISIBLE);

                    //work out duration
                    ZoneId z = ZoneId.of("Australia/Sydney");
                    ZonedDateTime now = ZonedDateTime.now(z);
                    LocalDate tomorrow = now.toLocalDate().plusDays(1);
                    ZonedDateTime tomorrowStart = tomorrow.atStartOfDay(z);
                    long duration = Duration.between(now,tomorrowStart ).toMillis();

                    //countdown timer
                    new CountDownTimer(duration * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String time = String.format(Locale.getDefault(),"%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                                    final String[] hourMinSec = time.split(":");

                                }
                            });
                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();


                    tvQuestionNumber.setText("5/5");
                    tvQuestion.setText("\n\nCheck-In Complete! Come back in\ntime");
                    ivImage.setImageResource(R.drawable.clock);
                } else {
                    currentQuestionPosition++;
                    //Assign a question from the bank
                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            for(CheckInQuestion q : CheckInQuestion.getCheckInQuestionList()){
                                qDB.checkInQuestionDao().insert(q);
                            }
                            qID = "q"+ currentQuestionPosition;
                            CheckInQuestion desiredQuestion = qDB.checkInQuestionDao().getCheckInQuestion(qID);
                            String checkInQuestion = desiredQuestion.getQuestion();
                            Integer checkInImage = desiredQuestion.getImage();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvQuestionNumber.setText(currentQuestionPosition+"/5");
                                    tvQuestion.setText(checkInQuestion);
                                    ivImage.setImageResource(checkInImage);
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    public void goToStudentHomeActivity(View view){
        Intent intent = new Intent (this, StudentHomeActivity.class);
        startActivity(intent);
    }

    public void goToStudentProfileActivity(View view){
        Intent intent = new Intent (this, StudentProfileActivity.class);
        startActivity(intent);
    }
}