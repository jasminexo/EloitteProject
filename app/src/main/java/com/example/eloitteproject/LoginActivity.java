package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
        switch (view.getId()) {
            case R.id.btnLogin:
                loginUser();
                break;
        }
    }
    //LOGIN FUNCTION: method for user login
    //video reference: CodeWithMazn (2020), https://www.youtube.com/watch?v=Z-RE1QuUWPg
    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        //error msg if no email inputted
        if (email.isEmpty()) {
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            return;
        }
        //error msg if email does not contain .com or @
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please provide a valid email!");
            etEmail.requestFocus();
            return;
        }
        //error msg if no password inputted
        if (password.isEmpty()) {
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            return;
        }

        //make progressBar visible when user clicks login for user feedback
        progressBar.setVisibility(View.VISIBLE);
        //Firebase checks email and password
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    //redirects to home activity
                    startActivity(new Intent(LoginActivity.this, StudentHomeActivity.class));
                    //creates a toast to provide feedback if login successful or not
                    Toast.makeText(getApplicationContext(), "Successful Login!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                } else {
                    Toast.makeText(getApplicationContext(), "Failed to login! Please check your credentials!",
                            Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    //redirects to the RegisterActivity
    public void goToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        //Removes animation when switching to registerActivity
        overridePendingTransition(0, 0);
    }
}
