package com.example.vkira.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Animation mAnimation;
    Button mZoomIn ,mZoomOut ,mFadeIn , mFadeOut , mMovex , mMovey , mRotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mZoomIn = findViewById(R.id.zoomin);
        mZoomOut = findViewById(R.id.zoomout);
        mFadeIn = findViewById(R.id.fadein);
        mFadeOut = findViewById(R.id.fadeout);
        mMovex = findViewById(R.id.movex);
        mMovey = findViewById(R.id.movey);
        mRotate = findViewById(R.id.rotate);


        mZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this , R.anim.zoomin);
                mZoomIn.startAnimation(mAnimation);
            }
        });

        mZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this , R.anim.zoomout);
                mZoomOut.startAnimation(mAnimation);
            }
        });

        mFadeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this , R.anim.fadein);
                mFadeIn.startAnimation(mAnimation);
            }
        });

        mFadeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this , R.anim.fadeout);
                mFadeOut.startAnimation(mAnimation);
            }
        });

        mMovex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this , R.anim.movex);
                mMovex.startAnimation(mAnimation);
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this , R.anim.movexreverse);
                mMovex.startAnimation(mAnimation);
            }
        });

        mMovey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this , R.anim.movey);
                mMovey.startAnimation(mAnimation);
            }
        });

        mRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimation = AnimationUtils.loadAnimation(MainActivity.this , R.anim.rotateclockwise);
                mRotate.startAnimation(mAnimation);
            }
        });

    }
}
