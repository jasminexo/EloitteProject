package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class TeacherGoalsAddActivity extends AppCompatActivity {

    EditText etTitle, etDesc, etDeadline;
    Button btnCreateGoal, btnCancelGoal;
    DatabaseReference reference;
    Integer goalsNum = new Random().nextInt();
    String keygoals = Integer.toString(goalsNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_goals_add);

        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);
        etDeadline = findViewById(R.id.etDeadline);

        btnCreateGoal = findViewById(R.id.btnCreateGoal);
        btnCancelGoal = findViewById(R.id.btnCancelGoal);

        btnCancelGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherGoalsAddActivity.this, TeacherGoalsActivity.class);
                startActivity(intent);
            }
        });

        btnCreateGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insert data into the database
                reference = FirebaseDatabase.getInstance().getReference().child("GoalsAdd")
                        .child("Goals" + goalsNum);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("titlegoals").setValue(etTitle.getText().toString());
                        dataSnapshot.getRef().child("descgoals").setValue(etDesc.getText().toString());
                        dataSnapshot.getRef().child("dategoals").setValue(etDeadline.getText().toString());
                        dataSnapshot.getRef().child("keygoals").setValue(keygoals);

                        Intent intent = new Intent(TeacherGoalsAddActivity.this, TeacherGoalsActivity.class);
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