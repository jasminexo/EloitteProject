package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.concurrent.Executors;

public class StudentPersonalityQuizDetailActivity extends AppCompatActivity {

    private TextView tvQuestionNumber, tvQuestion;
    private ImageView ivImage;
    private RadioButton rbOptionA, rbOptionB, rbOptionC;
    private Button btnNext, btnReturnHome;
    private String qID;
    PersonalityQuizQuestionDatabase personalityQuizDB;
    int currentScore = 0, currentQuestionPosition = 1;
    int q1Score, q2Score, q3Score, q4Score, q5Score, q6Score, q7Score, q8Score, q9Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_personality_quiz_detail);

        tvQuestion = findViewById(R.id.tvQTitle);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        ivImage = findViewById(R.id.ivImage);
        rbOptionA = findViewById(R.id.rbOptionA);
        rbOptionB = findViewById(R.id.rbOptionB);
        rbOptionC = findViewById(R.id.rbOptionC);
        btnNext = findViewById(R.id.btnNext);
        btnReturnHome = findViewById(R.id.btnReturnHome);

        //Database
        personalityQuizDB = Room.databaseBuilder(getApplicationContext(), PersonalityQuizQuestionDatabase.class ,
                "personality-quiz-question-database")
                .build();

        displayCheckInQuestion();
        nextButtonClicked();

    }

    private void displayCheckInQuestion() {
        btnNext.setVisibility(View.VISIBLE);

        if (currentQuestionPosition == 1) {
            btnReturnHome.setVisibility(View.GONE);
        }
        //Assign a question from the bank
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                for(PersonalityQuizQuestion q : PersonalityQuizQuestion.getPersonalityQuizQuestionList()){
                    personalityQuizDB.PersonalityQuizQuestionDao().insert(q);
                }
                qID = "q"+ currentQuestionPosition;
                PersonalityQuizQuestion desiredQuestion = personalityQuizDB.PersonalityQuizQuestionDao().getPersonalityQuizQuestion(qID);
                String personalityQuestion = desiredQuestion.getQuestion();
                Integer personalityQuizImage = desiredQuestion.getImage();
                String optionA = desiredQuestion.getOption1();
                String optionB = desiredQuestion.getOption2();
                String optionC = desiredQuestion.getOption3();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvQuestionNumber.setText(currentQuestionPosition+"/9");
                        tvQuestion.setText(personalityQuestion);
                        ivImage.setImageResource(personalityQuizImage);
                        rbOptionA.setText(optionA);
                        rbOptionB.setText(optionB);
                        rbOptionC.setText(optionC);
                    }
                });
            }
        });
    }

    private void nextButtonClicked() {
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