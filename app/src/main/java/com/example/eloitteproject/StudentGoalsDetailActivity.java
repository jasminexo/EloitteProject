package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class StudentGoalsDetailActivity extends AppCompatActivity {

    EditText etTitle, etDesc, etDeadline;
    Button btnCreateGoal, btnCancelGoal;
    DatabaseReference reference;
    Integer goalsNum = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_goals_detail);

        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);
        etDeadline = findViewById(R.id.etDeadline);

        btnCreateGoal = findViewById(R.id.btnCreateGoal);
        btnCancelGoal = findViewById(R.id.btnCancelGoal);

        btnCreateGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insert data into the database
                reference = FirebaseDatabase.getInstance().getReference().child("GoalsAdd")
                        .child("Goals" + goalsNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("titlegoals").setValue(etTitle.getText().toString());
                        dataSnapshot.getRef().child("descgoals").setValue(etDesc.getText().toString());
                        dataSnapshot.getRef().child("dategoals").setValue(etDeadline.getText().toString());

                        Intent intent = new Intent(StudentGoalsDetailActivity.this, StudentGoalsActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
            }
        });
    }
}