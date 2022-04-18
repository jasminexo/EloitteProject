package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.concurrent.Executors;

public class StudentPersonalityQuizDetailActivity extends AppCompatActivity {

    private TextView tvQuestionNumber, tvQuestion;
    private ImageView ivImage;
    private RadioButton rbOptionA, rbOptionB, rbOptionC;
    private RadioGroup rgOptions;
    private Button btnNext, btnReturnHome;
    private String qID, personalityType, personalityDesc, readMoreLink;
    PersonalityQuizQuestionDatabase personalityQuizDB;
    int totalScore = 0, currentQuestionPosition = 1;
    int q1Score = 0, q2Score = 0, q3Score = 0, q4Score = 0, q5Score = 0, q6Score = 0, q7Score = 0, q8Score = 0, q9Score = 0;

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
        rgOptions = findViewById(R.id.rgOptions);
        btnNext = findViewById(R.id.btnNext);
        btnReturnHome = findViewById(R.id.btnReturnHome);

        //Database
        personalityQuizDB = Room.databaseBuilder(getApplicationContext(), PersonalityQuizQuestionDatabase.class,
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
                for (PersonalityQuizQuestion q : PersonalityQuizQuestion.getPersonalityQuizQuestionList()) {
                    personalityQuizDB.PersonalityQuizQuestionDao().insert(q);
                }
                qID = "q" + currentQuestionPosition;
                PersonalityQuizQuestion desiredQuestion = personalityQuizDB.PersonalityQuizQuestionDao().getPersonalityQuizQuestion(qID);
                String personalityQuestion = desiredQuestion.getQuestion();
                Integer personalityQuizImage = desiredQuestion.getImage();
                String optionA = desiredQuestion.getOption1();
                String optionB = desiredQuestion.getOption2();
                String optionC = desiredQuestion.getOption3();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvQuestionNumber.setText(currentQuestionPosition + "/9");
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
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentQuestionPosition == 9) {
                    if (rbOptionA.isChecked()) {
                        q9Score = 1;
                    } else if (rbOptionB.isChecked()) {
                        q9Score = 2;
                    } else if (rbOptionC.isChecked()) {
                        q9Score = 3;
                    } else {
                        Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                    }
                    totalScore = q1Score + q2Score + q3Score + q4Score + q5Score + q6Score + q7Score + q8Score + q9Score;
                    if (totalScore <= 9) {
                        personalityType = "You got: The HYPERKINETIC Type";
                        personalityDesc = "To control your stress and regain your focus, you as the Hyperkinetic Type, need to find ways to calm your mind and relax your body. This can be achieved by:" +
                                "\n - Making sure you get enough sleep" +
                                "\n - Take breaks, and practice breathing exercises or meditation regularly" +
                                "\n - Exercise frequently";
                        readMoreLink = "https://www.entrepreneur.com/article/324560";
                    } else if (totalScore <= 18) {
                        personalityType = "You got: The FLAMMABLE Type";
                        personalityDesc = "As a Flammable type, you will help best improve your mental health and well-being by: " +
                                "\n - Spending more time with happy, positive people" +
                                "\n - Have frequent breaks to prevent burn-out";
                        readMoreLink = "https://www.atmospherefitness.com.au/31-ways-to-de-stress/";
                    } else {
                        personalityType = "You got: The EARTHY Type";
                        personalityDesc = "As an Earthy type, you will help best improve your mental health and well-being by:" +
                                "\n - Have breaks and spend time outside (e.g. go for a short walk)" +
                                "\n - Take time doing slow exercises and away from learning will help significantly (idleness is very helpful)";
                        readMoreLink = "https://medium.com/borealism/connecting-the-dots-why-an-idle-mind-is-more-important-than-ever-14c5f7c99460";
                    }
                    //show results in bottom bar popup
                    showResultBottomScreen();
                } else {
                    //saving the score for each question - need to find a way to upload into database with date and time
                    if (currentQuestionPosition == 1) {
                        if (rbOptionA.isChecked()) {
                            q1Score = 1;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionB.isChecked()) {
                            q1Score = 2;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionC.isChecked()) {
                            q1Score = 3;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (currentQuestionPosition == 2) {
                        if (rbOptionA.isChecked()) {
                            q2Score = 1;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionB.isChecked()) {
                            q2Score = 2;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionC.isChecked()) {
                            q2Score = 3;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else {
                            Toast.makeText(StudentPersonalityQuizDetailActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (currentQuestionPosition == 3) {
                        if (rbOptionA.isChecked()) {
                            q3Score = 1;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionB.isChecked()) {
                            q3Score = 2;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionC.isChecked()) {
                            q3Score = 3;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else {
                            Toast.makeText(StudentPersonalityQuizDetailActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (currentQuestionPosition == 4) {
                        if (rbOptionA.isChecked()) {
                            q4Score = 1;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionB.isChecked()) {
                            q4Score = 2;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionC.isChecked()) {
                            q4Score = 3;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else {
                            Toast.makeText(StudentPersonalityQuizDetailActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (currentQuestionPosition == 5) {
                        if (rbOptionA.isChecked()) {
                            q5Score = 1;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionB.isChecked()) {
                            q5Score = 2;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionC.isChecked()) {
                            q5Score = 3;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else {
                            Toast.makeText(StudentPersonalityQuizDetailActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (currentQuestionPosition == 6) {
                        if (rbOptionA.isChecked()) {
                            q6Score = 1;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionB.isChecked()) {
                            q6Score = 2;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionC.isChecked()) {
                            q6Score = 3;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else {
                            Toast.makeText(StudentPersonalityQuizDetailActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (currentQuestionPosition == 7) {
                        if (rbOptionA.isChecked()) {
                            q7Score = 1;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionB.isChecked()) {
                            q7Score = 2;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionC.isChecked()) {
                            q7Score = 3;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else {
                            Toast.makeText(StudentPersonalityQuizDetailActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (currentQuestionPosition == 8) {
                        if (rbOptionA.isChecked()) {
                            q8Score = 1;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionB.isChecked()) {
                            q8Score = 2;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else if (rbOptionC.isChecked()) {
                            q8Score = 3;
                            currentQuestionPosition++;
                            displayCheckInQuestion();
                        } else {
                            Toast.makeText(StudentPersonalityQuizDetailActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                    }
                    rgOptions.clearCheck();
                }
            }
        });
    }

    //shows the results in bottom screen
    private void showResultBottomScreen() {
        //Instantiate new bottom sheet dialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(StudentPersonalityQuizDetailActivity.this);
        //Inflate the view so that users are able to view it
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.personality_result, (LinearLayout) findViewById(R.id.llBook));
        TextView tvPersonalityType = bottomSheetView.findViewById(R.id.tvPersonalityType);
        tvPersonalityType.setText(personalityType);
        TextView tvPersonalityDesc = bottomSheetView.findViewById(R.id.tvPersonalityDesc);
        tvPersonalityDesc.setText(personalityDesc);

        Button btnSuggestions = bottomSheetView.findViewById(R.id.btnSuggestions);
        //when read more button clicked, open browser
        btnSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(readMoreLink));
                startActivity(intent);
            }
        });

        //returns to student home screen
        Button btnReturnToHome = bottomSheetView.findViewById(R.id.btnReturntoHome);
        btnReturnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentPersonalityQuizDetailActivity.this, StudentHomeActivity.class);
                startActivity(intent);
                bottomSheetDialog.dismiss();
            }
        });
        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    public void goToStudentHomeActivity(View view) {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }

    public void goToStudentProfileActivity(View view) {
        Intent intent = new Intent(this, StudentProfileActivity.class);
        startActivity(intent);
    }
}