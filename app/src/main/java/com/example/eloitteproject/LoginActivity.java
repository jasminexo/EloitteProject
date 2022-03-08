package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;
    EditText etEmail, etPassword;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmailLogin);
        etPassword = findViewById(R.id.etPassword);

        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {

    }


    //redirects to the RegisterActivity
    public void goToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        //Removes animation when switching to registerActivity
        overridePendingTransition(0, 0);
    }
}
