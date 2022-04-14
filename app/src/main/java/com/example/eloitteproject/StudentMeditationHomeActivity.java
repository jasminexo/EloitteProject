package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class StudentMeditationHomeActivity extends AppCompatActivity implements VideoAdapter.ClickListener {

    public ArrayList<Video> mVideo = Video.getVideo();
    public RecyclerView mRecyclerView;
    public VideoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_meditation_home);

        //Instantiate RecyclerView
        mRecyclerView = findViewById(R.id.rvMeditation);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new VideoAdapter(this, mVideo, this);
        mRecyclerView.setAdapter(mAdapter);
//        ibMenu = findViewById(R.id.ibMenu);
//
//        ibMenuClicked();

    }

    //when user clicks on an item, data is passed as an intent and opens activity
    @Override
    public void onClick(int position) {
        String msg = mVideo.get(position).getId();
        Intent intent = new Intent(this, StudentMeditationDetailActivity.class);
        intent.putExtra("transferMsg", msg);
        startActivity(intent);
    }


    public void goToStudentHomeActivity(View view){
        Intent intent = new Intent (this, StudentHomeActivity.class);
        startActivity(intent);
    }

    public void goToStudentProfileActivity(View view){
        Intent intent = new Intent (this, StudentProfileActivity.class);
        startActivity(intent);
    }

}
