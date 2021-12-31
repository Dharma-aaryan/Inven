package com.sir.dbms_mini.ExtraActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.sir.dbms_mini.ExtraActivities.IntroPage;
import com.sir.dbms_mini.R;

public class LogoActivity extends AppCompatActivity {
    ImageView img1;
    Animation top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_logo);

        img1 = (ImageView)findViewById(R.id.logo);
        top = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo);
        img1.setAnimation(top);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), IntroPage.class));
                finish();
            }
        }, 2000);
    }
}