package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.Executors;

public class SplashScreenActivity extends AppCompatActivity {

    Button btnLogin, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Initialising widgets
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        //Create user database
        UserDatabase uDB = Room.databaseBuilder(getApplicationContext(), UserDatabase.class,
                "user-database")
                .fallbackToDestructiveMigration()
                .build();

        //Make an insert for new users
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //Only insert users if they don't already exist in the databse
                if (uDB.userDao().getUserCount() > 0) {
                    System.out.println("Users already inserted!");
                } else {
                    uDB.userDao().insertAll(User.getUserList());
                }

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreenActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}
