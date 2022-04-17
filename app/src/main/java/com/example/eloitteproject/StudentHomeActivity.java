package com.example.eloitteproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Calendar;
import java.util.concurrent.Executors;

public class StudentHomeActivity extends AppCompatActivity {
    TextView studentName;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId, previousActivity;
    int clickID;
    int dayClicked=0;
    int incompleteCurrentQPosition = 1;
    int completeCurrentQPosition = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        studentName = findViewById(R.id.tvStudentName);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                studentName.setText("Hello " + (documentSnapshot.getString( "FullName") + "!"));
            }
        });

        Button logout = findViewById(R.id.btn_logout);
        //Button leaderboard = findViewById(R.id.btnLeaderboard);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        //getting the last day student completed check-in quiz
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            dayClicked = extras.getInt("receiveLastClickDay", 0);
            previousActivity = extras.getString("from_activity");
        } else {
            previousActivity = "studentHOMEActivity";
        }

        if (previousActivity.equals("studentCheckInActivity")){
            completeCurrentQPosition = 6;
        } else if (previousActivity.equals("studentHOMEActivity")){
            incompleteCurrentQPosition = 1;
            dayClicked = 0;
        }

    }

    public void goToCheckInActivity(View view){
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);

        if (dayClicked != 0 && dayClicked != today){
            Intent intent = new Intent (this, StudentCheckInActivity.class);
            intent.putExtra("receiveCurrentQPosition", incompleteCurrentQPosition);
            startActivity(intent);
        } else if (dayClicked != 0 && dayClicked == today){
            Intent intent = new Intent (this, StudentCheckInActivity.class);
            intent.putExtra("receiveCurrentQPosition", completeCurrentQPosition);
            startActivity(intent);
        } if (dayClicked == 0){
            Intent intent = new Intent (this, StudentCheckInActivity.class);
            intent.putExtra("receiveCurrentQPosition", incompleteCurrentQPosition);
            startActivity(intent);
        }

        clickID = 1;
        update();
    }

    public void goToQuizActivity(View view){
        Intent intent = new Intent (this, StudentPersonalityQuizHomeActivity.class);
        startActivity(intent);

        clickID = 1;
        update();
    }

    public void goToStudentGoalsActivity(View view){
        Intent intent = new Intent (this, StudentGoalsActivity.class);
        startActivity(intent);

        clickID = 1;
        update();
    }

    public void goToLeaderboardActivity(View view){
        Intent intent = new Intent (this, LeaderboardActivity.class);
        startActivity(intent);

//        clickID=1;
//        update();
    }

    public void goToMeditationActivity(View view){
        Intent intent = new Intent (this, StudentMeditationHomeActivity.class);
        startActivity(intent);

        clickID = 1;
        update();
    }

    public void goToStudentProfileActivity(View view){
        Intent intent = new Intent (this, StudentProfileActivity.class);
        startActivity(intent);

        clickID = 1;
        update();

    }

    public void goToStudentAppointmentActivity(View view){
        Intent intent = new Intent (this, StudentAppointmentActivity.class);
        startActivity(intent);

        clickID = 1;
        update();

    }
    //testing

    //Update the user database with points based on current logged in users
    public void update() {

        //Update user database
        UserDatabase uDB = Room.databaseBuilder(getApplicationContext(), UserDatabase.class ,
                "user-database")
                .fallbackToDestructiveMigration()
                .build();

        String userID = FirebaseAuth.getInstance().getUid();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                uDB.userDao().updateScore(Click.getClick(clickID), userID);
            }
        });
    }
}