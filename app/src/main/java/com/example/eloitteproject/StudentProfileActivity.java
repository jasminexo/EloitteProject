package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.concurrent.Executors;

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

        //User database
        UserDatabase uDB = Room.databaseBuilder(getApplicationContext(), UserDatabase.class,
                "user-database")
                .fallbackToDestructiveMigration()
                .build();

        String userID = FirebaseAuth.getInstance().getUid();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        String fullName = uDB.userDao().getName(userID);
                        String email = uDB.userDao().getEmail(userID);
                        String DOB = uDB.userDao().getDOB(userID);
                        String parentEmail = uDB.userDao().getParentEmail(userID);
                        int profilePic = uDB.userDao().getProfilePic(userID);
                        //String profileBG = uDB.userDao().getProfileBG(userID);
                        //String bgColour = "R.color."+profileBG;

                        tvProfileName.setText(fullName, TextView.BufferType.EDITABLE);
                        etUserFullName.setText(fullName, TextView.BufferType.EDITABLE);
                        etUserEmail.setText(email, TextView.BufferType.EDITABLE);
                        etUserDOB.setText(DOB, TextView.BufferType.EDITABLE);
                        etUserParentContactEmail.setText(parentEmail, TextView.BufferType.EDITABLE);

                        ivProfilePic.setImageResource(profilePic);

                        //Drawable unwrappedDrawable = tvProfilePicBackground.getBackground();
                        //Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                        //DrawableCompat.setTint(wrappedDrawable, (getResources().getColor(Integer.parseInt(bgColour))));

                    }
                });
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void goToStudentHomeActivity(View view) {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }

    public void goToProfilePicSelection(View view) {
        Intent intent = new Intent(this, ProfilePicSelectionActivity.class);
        startActivity(intent);
    }

    public void updateProfile() {

        //Update user database
        UserDatabase uDB = Room.databaseBuilder(getApplicationContext(), UserDatabase.class,
                "user-database")
                .fallbackToDestructiveMigration()
                .build();

        String userID = FirebaseAuth.getInstance().getUid();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                uDB.userDao().updateName(etUserFullName.getText().toString(), userID);
                uDB.userDao().updateEmail(etUserEmail.getText().toString(), userID);
                uDB.userDao().updateDOB(etUserDOB.getText().toString(), userID);
                uDB.userDao().updateParentEmail(etUserParentContactEmail.getText().toString(), userID);
            }
        });
    }
}