package com.example.vkira.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button mLaunchMap, mLaunchMarket , mSendMail , mShareIcon , mShareImage , mShareMedia;
    Intent mIntent , mChooser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLaunchMap = findViewById(R.id.LaunchMap);
        mLaunchMarket = findViewById(R.id.LaunchMarket);
        mSendMail = findViewById(R.id.SendMail);
        mShareIcon = findViewById(R.id.ShareIcon);
        mShareImage = findViewById(R.id.ShareImages);
        mShareMedia = findViewById(R.id.ShareMedia);


        //Launching Map Application for Static Longitude,Latitude
        mLaunchMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntent = new Intent(Intent.ACTION_VIEW);
                mIntent.setData(Uri.parse("geo:47.6,-122.3"));
                mChooser = Intent.createChooser(mIntent , "Select Application");

                try{
                    startActivity(mChooser);
                }
                catch (Exception x){
                    x.printStackTrace();
                }
            }
        });


        //Launching market application for static Uri
        mLaunchMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntent = new Intent(Intent.ACTION_VIEW);
                mIntent.setData(Uri.parse("market://details?id=com.duckduckgo.mobile.android&hl=en"));
                mChooser = Intent.createChooser(mIntent , "Select Store");
                try{
                    startActivity(mChooser);
                }
                catch (Exception x){
                    x.printStackTrace();
                }
            }
        });

        //Sending Simple mail
        mSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to[] = {"vkiranmaniya@gmai.com" ,"vkiranmaniya@outlook.com"};
                mIntent = new Intent(Intent.ACTION_SEND);
                mIntent.setData(Uri.parse("mailto:"));
                mIntent.putExtra(Intent.EXTRA_EMAIL , to);
                mIntent.putExtra(Intent.EXTRA_SUBJECT , "Sent Via Test Application");
                mIntent.putExtra(Intent.EXTRA_TEXT , "Hey, This is message from your application");
                mIntent.setType("message/rfc822");

                mChooser = Intent.createChooser(mIntent , "Select Mail Application");
                try{
                    startActivity(mChooser);
                }
                catch (Exception x){
                    x.printStackTrace();
                }

            }
        });


        //Sharing Images from Mail Imatent
        mShareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mIconUri = Uri.parse("android.resource://com.example.vkira.implicitintents/"+R.drawable.a);
                mIntent = new Intent(Intent.ACTION_SEND);
                mIntent.setType("image/*");
                mIntent.putExtra(Intent.EXTRA_STREAM , mIconUri);
                mIntent.putExtra(Intent.EXTRA_TEXT , "This is the Application Icon");
                mChooser = Intent.createChooser(mIntent , "Select Sender Application");
                try{
                    startActivity(mChooser);
                }
                catch (Exception x){
                    x.printStackTrace();
                }

            }
        });

        //Sharing Images from File Explorer
        mShareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Uri> mUriList= new ArrayList<Uri>();
                File mPictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String mPictureList[] = mPictures.list();

                if(mPictureList != null){

                    for(String fileName : mPictureList){
                        mUriList.add(Uri.parse("file://"+mPictures.toString()+"/ "+fileName));
                    }

                    mIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                    mIntent.setType("image/*");
                    mIntent.putExtra(Intent.EXTRA_STREAM , mUriList);
                    mChooser = Intent.createChooser(mIntent , "Select Application");

                    try{
                        startActivity(mChooser);
                    }
                    catch (Exception x){
                        x.printStackTrace();
                    }
                }

            }
        });
    }
}
