package com.example.eloitteproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class StudentMeditationDetailActivity extends AppCompatActivity {
    private YouTubePlayerView youTubePlayerView;
    Button ibMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_meditation_detail);

        //calls upon string that was passed with the intent
        Intent intent = getIntent();
        String msg = intent.getStringExtra("transferMsg");
        Video video = Video.getName(msg);

        TextView name = findViewById(R.id.tvName);
        name.setText(video.getName());

        TextView desc = findViewById(R.id.tvDesc);
        desc.setText(video.getDesc());

        TextView source = findViewById(R.id.tvSource);
        source.setText(video.getSource());

        ImageView picture = findViewById(R.id.ivPicture);
        picture.setImageResource(video.getPicture());

//        ibMenuClicked();

        //youtube player API-reference:Pierfrancesco Soffritti, https://github.com/PierfrancescoSoffritti/android-youtube-player
        //YOUTUBE FUNCTION - embeds video into ResourceDetailActivity
        youTubePlayerView = findViewById(R.id.activity_youtubePlayerView);
        //ensures the video stops playing when exited
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String vId = video.getVideoId();
                //loads video automatically when someone clicks the item row
                youTubePlayer.loadVideo(vId, 0);
            }
        });

    }

    public void goToStudentHomeActivity(View view) {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }
}