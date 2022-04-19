package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TeacherHomeActivity extends AppCompatActivity {
    TextView teacherName;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        teacherName = findViewById(R.id.tvStudentName);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                teacherName.setText("Hello " + (documentSnapshot.getString("FullName") + "!"));
            }
        });
        logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        return true;
                    case R.id.allStudents:
                        startActivity(new Intent(getApplicationContext(), LeaderboardActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.appointments:
                        startActivity(new Intent(getApplicationContext(), TeacherAppointmentActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.goals:
                        startActivity(new Intent(getApplicationContext(), TeacherGoalsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public void logoutAdmin(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void goToEmailActivity(View view) {
        Intent intent = new Intent(this, TeacherEmailActivity.class);
        startActivity(intent);
    }

    public void goToStudentGoalsActivity(View view) {
        Intent intent = new Intent(this, TeacherGoalsActivity.class);
        startActivity(intent);
    }


}