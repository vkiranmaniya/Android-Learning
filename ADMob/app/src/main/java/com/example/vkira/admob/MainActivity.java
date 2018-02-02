package com.example.vkira.admob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    AdView mAdView;
    Button mGetFull;
    InterstitialAd mInterstatialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(MainActivity.this , String.valueOf(R.string.admob_appid));
        mAdView = findViewById(R.id.AdView);
        AdRequest mAdRequest = new AdRequest.Builder().addTestDevice("09353489A85BB6094D51A3F397DB6373").build();
        mAdView.loadAd(mAdRequest);

        mInterstatialAd = new InterstitialAd(MainActivity.this);
        mInterstatialAd.setAdUnitId(String.valueOf(R.string.interestrialactivity_ad_unit));
        mInterstatialAd.loadAd(mAdRequest);

        mInterstatialAd.setAdListener( new AdListener(){
            public void onAdClosed(){
                startActivity(new Intent(MainActivity.this , InterestrialActivity.class));
                mInterstatialAd.loadAd(new AdRequest.Builder().addTestDevice("09353489A85BB6094D51A3F397DB6373").build());
            }
        });

        mGetFull = findViewById(R.id.gtn_terre);
        mGetFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInterstatialAd.isLoaded()){
                    mInterstatialAd.show();
                }
            }
        });
    }
}
