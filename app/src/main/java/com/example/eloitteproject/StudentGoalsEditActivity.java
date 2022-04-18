package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentGoalsEditActivity extends AppCompatActivity {

    EditText editTitle, editDesc, editDeadline;
    Button btnSaveUpdate, btnDelete;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_goals_edit);

        editTitle = findViewById(R.id.etTitleEdit);
        editDesc = findViewById(R.id.etDescEdit);
        editDeadline = findViewById(R.id.etDeadlineEdit);

        btnSaveUpdate = findViewById(R.id.btnSaveUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        //get a value from previous page
        editTitle.setText(getIntent().getStringExtra("titlegoals"));
        editDesc.setText(getIntent().getStringExtra("descgoals"));
        editDeadline.setText(getIntent().getStringExtra("dategoals"));

        final String kGoals = getIntent().getStringExtra("keygoals");

        reference = FirebaseDatabase.getInstance().getReference().child("GoalsAdd")
                .child("Goals" + kGoals);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(StudentGoalsEditActivity.this, StudentGoalsActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to delete!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        //update goals button
        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titlegoals").setValue(editTitle.getText().toString());
                        dataSnapshot.getRef().child("descgoals").setValue(editDesc.getText().toString());
                        dataSnapshot.getRef().child("dategoals").setValue(editDeadline.getText().toString());
                        dataSnapshot.getRef().child("keygoals").setValue(kGoals);
                        Intent intent = new Intent(StudentGoalsEditActivity.this, StudentGoalsActivity.class);
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