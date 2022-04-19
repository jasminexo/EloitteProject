package com.example.eloitteproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilePicSelectionActivity extends AppCompatActivity {

    private ImageView ivProfilePic;
    private TextView tvProfilePicBackground;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pic_selection);

        ivProfilePic = findViewById(R.id.ivProfilePic);
        tvProfilePicBackground = findViewById(R.id.tvProfilePicBackground);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
            }

            //need to retrieve animal and colour from user database

        });
    }

    public void ivBearSelected(View view) {
        ivProfilePic.setImageDrawable(getResources().getDrawable(R.drawable.bear));
    }

    public void ivPenguinSelected(View view) {
        ivProfilePic.setImageDrawable(getResources().getDrawable(R.drawable.penguin));
    }

    public void ivFoxSelected(View view) {
        ivProfilePic.setImageDrawable(getResources().getDrawable(R.drawable.fox));
    }

    public void ivBunnySelected(View view) {
        ivProfilePic.setImageDrawable(getResources().getDrawable(R.drawable.bunny));
    }

    public void ivHedgehogSelected(View view) {
        ivProfilePic.setImageDrawable(getResources().getDrawable(R.drawable.hedgehog));
    }

    public void ivOwlSelected(View view) {
        ivProfilePic.setImageDrawable(getResources().getDrawable(R.drawable.owl));
    }

    public void tvBrownSelected(View view) {
        Drawable unwrappedDrawable = tvProfilePicBackground.getBackground();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, (getResources().getColor(R.color.circle_brown)));
    }

    public void tvBeigeSelected(View view) {
        Drawable unwrappedDrawable = tvProfilePicBackground.getBackground();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, (getResources().getColor(R.color.circle_beige)));
    }

    public void tvYellowSelected(View view) {
        Drawable unwrappedDrawable = tvProfilePicBackground.getBackground();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, (getResources().getColor(R.color.circle_yellow)));
    }

    public void tvOrangeSelected(View view) {
        Drawable unwrappedDrawable = tvProfilePicBackground.getBackground();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, (getResources().getColor(R.color.circle_orange)));
    }

    public void tvPurpleSelected(View view) {
        Drawable unwrappedDrawable = tvProfilePicBackground.getBackground();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, (getResources().getColor(R.color.circle_purple)));
    }

    public void tvBlueSelected(View view) {
        Drawable unwrappedDrawable = tvProfilePicBackground.getBackground();
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, (getResources().getColor(R.color.circle_blue)));
    }

    public void goToStudentHomeActivity(View view) {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }
}