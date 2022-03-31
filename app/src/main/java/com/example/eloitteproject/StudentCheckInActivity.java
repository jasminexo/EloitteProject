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
    private Button btnNext;
    private SeekBar seekBar;
    int currentScore = 0, currentQuestionPosition = 1, seekBarProgress = 3, currentArrayPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_checkin);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        ivImage = findViewById(R.id.ivImage);
        btnNext = findViewById(R.id.btnNext);
        seekBar = findViewById(R.id.seekBar);

        seekBarChanged();
        displayCheckInQuestion();

    }

    private void displayCheckInQuestion() {

        //Database
        CheckInQuestionDatabase qDB = Room.databaseBuilder(getApplicationContext(), CheckInQuestionDatabase.class ,
                "check-in-question-database")
                .build();

        //Assign a question from the bank
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                for(CheckInQuestion q : CheckInQuestion.getCheckInQuestionList()){
                        qDB.checkInQuestionDao().insert(q);
                }
                String qID = "q"+ currentQuestionPosition;
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

    public void goToStudentHomeActivity(View view){
        Intent intent = new Intent (this, StudentHomeActivity.class);
        startActivity(intent);
    }

    public void goToStudentProfileActivity(View view){
        Intent intent = new Intent (this, StudentProfileActivity.class);
        startActivity(intent);
    }
}