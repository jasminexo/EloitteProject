package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TeacherEmailActivity extends AppCompatActivity {

    EditText textToUser, textSubject, emailMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_email);

        textToUser = findViewById(R.id.et_to_user);
        textSubject = findViewById(R.id.et_subject);
        emailMessage = findViewById(R.id.et_message);

        Button buttonSend = findViewById(R.id.btn_send_email);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();

            }
        });
    }

    private void sendMail() {
        //separates emails when emailing multiple people
        String recipientList = textToUser.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = textSubject.getText().toString();
        String message = emailMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));

    }
}