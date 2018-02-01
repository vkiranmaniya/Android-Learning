package com.example.vkira.customtoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView mErrorBox;
    Button mShowToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mErrorBox = findViewById(R.id.error);
        mShowToast = findViewById(R.id.showToast);

        mShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast mToast = new Toast(MainActivity.this);
                mToast.setDuration(Toast.LENGTH_LONG);
                mToast.setGravity(Gravity.BOTTOM,0,0);

                LayoutInflater mInflator = getLayoutInflater();
                try{
                    View mView = mInflator.inflate(R.layout.toast_layout , (ViewGroup) findViewById(R.id.root));
                    mToast.setView(mView);
                    mToast.show();
                }
                catch (Exception x){
                    mErrorBox.setText(x.toString());
                }
            }
        });




    }
}
