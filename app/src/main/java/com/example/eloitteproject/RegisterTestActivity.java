package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterTestActivity extends AppCompatActivity {
    EditText fullName, email, password;
    Button registerBtn, goToLogin;
    boolean valid = true;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    CheckBox isTeacherBox, isStudentBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_test);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.et_fullname);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        registerBtn = findViewById(R.id.go_to_register);
        goToLogin = findViewById(R.id.login_btn);

        isTeacherBox = findViewById(R.id.cbTeacher);
        isStudentBox = findViewById(R.id.cb_student);

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
                    Toast.makeText(RegisterTestActivity.this, "Select The Account Type", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (valid) {
                    // start the user registration process
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText()
                            .toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterTestActivity.this, "Account Created",
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
                            if(isTeacherBox.isChecked()) {
                                startActivity(new Intent(getApplicationContext(), TeacherHomeActivity.class));
                            }
                            if(isStudentBox.isChecked()){
                                startActivity(new Intent(getApplicationContext(), StudentHomeActivity.class));

                            }
                        }
                    }).addOnFailureListener((new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterTestActivity.this, "Failed to Create Account",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }));
                }
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginTestActivity.class));
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



