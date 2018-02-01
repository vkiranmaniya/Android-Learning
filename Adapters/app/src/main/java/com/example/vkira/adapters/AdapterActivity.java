package com.example.vkira.adapters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class AdapterActivity extends AppCompatActivity {

    Intent mRecived;
    Spinner mSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        mRecived = getIntent();

        mSpinner = findViewById(R.id.mSpinner);
        ArrayList<String> country = new ArrayList<>();
        country.add("India");
        country.add("USA");
        country.add("Zimbambwe");
        country.add("UK");

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(AdapterActivity.this , R.layout.spinnerview ,country);
        mSpinner.setAdapter(mAdapter);

    }
}
