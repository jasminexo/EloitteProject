package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {
    EditText fullName, email, password;
    Button registerBtn, goToLogin, btnAsStudent, btnAsTeacher;
    boolean valid = true;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    CheckBox isTeacherBox, isStudentBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.et_fullname);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        registerBtn = findViewById(R.id.go_to_register);
        goToLogin = findViewById(R.id.login_btn);

        isTeacherBox = findViewById(R.id.cbTeacher);
        isStudentBox = findViewById(R.id.cb_student);

        btnAsStudent = findViewById(R.id.btnAsStudent);
        btnAsTeacher = findViewById(R.id.btnAsTeacher);

        //when student button clicked, set teacher button as grey, check student checkbox
        btnAsStudent.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                btnAsStudent.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.student, 0,0);
                btnAsStudent.setTextColor(getColor(R.color.orange_text));
                isStudentBox.setChecked(true);
                isStudentBox.setClickable(false);
                isStudentBox.setEnabled(false);

                btnAsTeacher.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.teacherbnw, 0,0);
                btnAsTeacher.setTextColor(getColor(R.color.grey_text));
            }
        });

        //when teacher button clicked, set student button as grey, check teacher checkbox
        btnAsTeacher.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                btnAsTeacher.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.teacher, 0,0);
                btnAsTeacher.setTextColor(getColor(R.color.orange_text));
                isTeacherBox.setChecked(true);
                isTeacherBox.setClickable(false);
                isTeacherBox.setEnabled(false);
                btnAsStudent.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.studentbnw, 0,0);
                btnAsStudent.setTextColor(getColor(R.color.grey_text));
            }
        });

        //check boxes logics
        isStudentBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    isTeacherBox.setChecked(false);

                }
            }
        });
        isTeacherBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    isStudentBox.setChecked(false);
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(fullName);
                checkField(email);
                checkField(password);

                //checkbox validation
                if(!(isTeacherBox.isChecked() || isStudentBox.isChecked())) {
                    Toast.makeText(RegisterActivity.this, "Select The Account Type", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (valid) {
                    // start the user registration process
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText()
                            .toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Account Created",
                                    Toast.LENGTH_SHORT).show();
                            DocumentReference df = fStore.collection("Users").document(user.getUid());
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("FullName", fullName.getText().toString());
                            userInfo.put("UserEmail", email.getText().toString());

                            //specify if the user is admin
                            if(isTeacherBox.isChecked()){
                                userInfo.put("isTeacher", "1");
                            }
                            if(isStudentBox.isChecked()){
                                userInfo.put("isStudent", "1");
                            }

                            df.set(userInfo);

                            String inputName = fullName.getText().toString();
                            String inputEmail = email.getText().toString();
                            String inputPassword = password.getText().toString();

                            Toast.makeText(RegisterActivity.this, "Login Success!", Toast.LENGTH_SHORT);

                            //User database
                            UserDatabase uDB = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user-database")
                                    .fallbackToDestructiveMigration()
                                    .build();
                            //Get the new users uID from Firebase authentication
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            String currentUID = firebaseUser.getUid();


                            if(isTeacherBox.isChecked()) {
                                startActivity(new Intent(getApplicationContext(), TeacherHomeActivity.class));
                            }
                            if(isStudentBox.isChecked()){
                                //Insert new user into the user database
                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        uDB.userDao().insertOneUser(new User(currentUID, inputName, inputPassword, inputEmail, 0,null, null, R.drawable.fox, "circle_purple"));
                                    }
                                });

                                startActivity(new Intent(getApplicationContext(), StudentHomeActivity.class));

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Failed to Create Account",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        } else {
            valid = true;

        }return valid;
    }
}



