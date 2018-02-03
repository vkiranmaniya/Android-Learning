package com.example.vkira.cascadedspinner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    LinearLayout mLinearLayout;
    Context mContext;
    Spinner mCountriesSpinner , mStateSpinner;
    SpinnerAdapter mSpinnerAdapter;
    ArrayList<String> mStateSpinnerData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountriesSpinner = findViewById(R.id.CountrySpinner);
        mStateSpinner = findViewById(R.id.StateSpinner);
        mTextView = findViewById(R.id.notification);
        mLinearLayout = findViewById(R.id.CascadedSpineer);
        mContext = getApplicationContext();

        String mStates[] = mContext.getResources().getStringArray(R.array.india_states);
        mStateSpinnerData.addAll(Arrays.asList(mStates));


        mCountriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String mCountry = parent.getSelectedItem().toString();
                if(mCountry.equals("India")){
                    ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(mContext , R.layout.spinnerlayout , mStateSpinnerData);
                    mStateSpinner.setAdapter(mAdapter);
                    mTextView.setVisibility(View.GONE);
                    mLinearLayout.setVisibility(View.VISIBLE);
                }
                else{
                    onNothingSelected(parent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mTextView.setText("Select India to Show Cascading of Spineer");
                mTextView.setVisibility(View.VISIBLE);
            }

        });
        
    }
}
