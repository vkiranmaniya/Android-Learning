package com.example.vkira.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    Button mAdapter , mBaseAdapter ,mRecyclerView ;
    Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = findViewById(R.id.Adapter);
        mBaseAdapter = findViewById(R.id.BaseAdapter);
        mRecyclerView = findViewById(R.id.RecyclerView);
        mContext = MainActivity.this;
        mIntent = new Intent(mContext , AdapterActivity.class);

        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mIntent);
            }
        });
    }
}
