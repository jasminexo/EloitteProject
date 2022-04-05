package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentGoalsActivity extends AppCompatActivity {

    Button btnAddGoals;

    DatabaseReference reference;
    RecyclerView rvGoals;
    ArrayList<Goals> list;
    GoalsAdapter goalsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_goals);

        btnAddGoals = findViewById(R.id.btnAddGoals);

        btnAddGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentGoalsActivity.this, StudentGoalsDetailActivity.class);
                startActivity(intent);
            }
        });

        //working with data
        rvGoals = findViewById(R.id.rvGoals);
        rvGoals.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Goals>();

        //get data from Firebase Realtime Database
        reference = FirebaseDatabase.getInstance().getReference().child("GoalsAdd");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //set code to retrieve data and replace layout
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Goals p = dataSnapshot1.getValue(Goals.class);
                        list.add(p);
                    }
                goalsAdapter = new GoalsAdapter(StudentGoalsActivity.this, list);
                rvGoals.setAdapter(goalsAdapter);
                goalsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                //set code to solve error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}