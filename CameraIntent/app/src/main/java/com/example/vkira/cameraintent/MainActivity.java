package com.example.vkira.cameraintent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button mCaptur;
    Intent mIntent;
    ImageView mDisaply;

    File mImageFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCaptur = findViewById(R.id.CaptureImage);
        mDisaply = findViewById(R.id.ImageDisply);

        mCaptur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) , "captured.jpg");
                Uri fileUri = Uri.fromFile(mImageFile);
                mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mIntent.putExtra(MediaStore.EXTRA_OUTPUT , fileUri);
                mIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY , 1);

                startActivityForResult(mIntent , 1);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            switch (resultCode){
                case Activity.RESULT_OK:
                        if(mImageFile.exists()){

                            Bitmap myBitmap = BitmapFactory.decodeFile(mImageFile.getAbsolutePath());

                            ImageView myImage = (ImageView) findViewById(R.id.imageviewTest);

                            myImage.setImageBitmap(myBitmap);

                        }
                        mDisaply.setText("Image has been Saved");
                        mDisaply.setVisibility(View.VISIBLE);
                        break;
                case Activity.RESULT_CANCELED:
                        mDisaply.setText("Operation Incomplate");
                        mDisaply.setVisibility(View.VISIBLE);
                        break;
                default:
                        mDisaply.setText("Error in Result Code");
                        mDisaply.setVisibility(View.VISIBLE);
                        break;
            }
        }
    }
}
