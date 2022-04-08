package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentProfileActivity extends AppCompatActivity {

    private User currentUser;
    private TextView tvProfileName, tvProfilePicBackground;
    private ImageView ivProfilePic;
    private EditText etUserFullName, etUserEmail, etUserDOB, etUserParentContactEmail;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfilePicBackground = findViewById(R.id.tvProfilePicBackground);
        ivProfilePic = findViewById(R.id.ivProfilePic);
        etUserFullName = findViewById(R.id.etUserFullName);
        etUserEmail = findViewById(R.id.etUserEmail);
        etUserDOB = findViewById(R.id.etUserDOB);
        etUserParentContactEmail = findViewById(R.id.etUserParentContactEmail);
        btnSave = findViewById(R.id.btnSave);

//        tvProfileName.setText(currentUser.getFullName());
    }

    public void goToStudentHomeActivity(View view){
        Intent intent = new Intent (this, StudentHomeActivity.class);
        startActivity(intent);
    }

    public void goToProfilePicSelection(View view){
        Intent intent = new Intent (this, ProfilePicSelectionActivity.class);
        startActivity(intent);
    }
}