package com.example.vkira.keybordcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    LinearLayout mMailBox , mMobileBox;
    EditText mUserEmail , mUserMobile , mUserName;
    TextView mWelcomeMessage , mFinalMessage;
    String mUser , mMail , mPhone;
    Button mSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMailBox = findViewById(R.id.EmailBox);
        mMobileBox = findViewById(R.id.MobileNumberBox);
        mUserEmail = findViewById(R.id.UserEmailAddress);
        mUserMobile = findViewById(R.id.UserMobileNumber);
        mUserName = findViewById(R.id.EnterNameEditText);
        mWelcomeMessage = findViewById(R.id.UserHelloText);
        mSubmit = findViewById(R.id.SubmitButton);
        mFinalMessage = findViewById(R.id.FinalMessage);

        mUserName.setOnEditorActionListener(this);
        mUserMobile.setOnEditorActionListener(this);
        mUserEmail.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView view, int i, KeyEvent keyEvent) {
        if(view.getId() == R.id.EnterNameEditText){
            mUser = view.getText().toString();
            mWelcomeMessage.setText("Hello "+mUser+" please enter your Email Id");
            mMailBox.setVisibility(View.VISIBLE);
        }
        if(view.getId() == R.id.UserEmailAddress){
            mMail = view.getText().toString();
            mMobileBox.setVisibility(View.VISIBLE);
        }
        if(view.getId() == R.id.MobileNumberBox){
            mPhone = view.getText().toString();
        }
        return true;
    }

    public void FinalizeVerification(View view){
        Toast.makeText(MainActivity.this, "listener called", Toast.LENGTH_LONG).show();
        mFinalMessage.setText("Hello "+mUser+" your verification is complated. for further information please check "+mPhone+" and mail box "+mMail+ ". Thank you");
        mFinalMessage.setVisibility(View.VISIBLE);
    }
}
