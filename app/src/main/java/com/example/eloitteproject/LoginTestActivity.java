package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginTestActivity extends AppCompatActivity {

    EditText fullName, email, password;
    Button loginBtn, goToRegister;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);

//        checkField(email);
//        checkField(password);
//
//        goToRegister.setOnClickListener();
    }
}