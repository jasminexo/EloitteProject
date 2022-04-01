package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class StudentCheckInActivity extends AppCompatActivity {

    private TextView tvQuestionNumber, tvQuestion;
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
            @Override
            public void onClick(View view) {
                if (currentQuestionPosition == 5){
                    btnNext.setVisibility(View.GONE);
                    btnReturnHome.setVisibility(View.VISIBLE);
                }
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