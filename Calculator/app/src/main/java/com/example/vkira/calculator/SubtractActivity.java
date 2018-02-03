package com.example.vkira.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubtractActivity extends AppCompatActivity {

    TextView result, final_result;
    EditText num2;
    Button sumBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract);

        Intent I = getIntent();
        final int output = I.getIntExtra("div", 1);
        result = findViewById(R.id.result);
        result.setText(output+" ");

        num2 = findViewById(R.id.snumberet);
        sumBtn = findViewById(R.id.additionbt);
        final_result = findViewById(R.id.final_result);


        sumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int number2 = Integer.parseInt(num2.getText().toString());
                try{
                    double div = output - number2;
                    final_result.setText(div+" ");
                    final_result.setVisibility(View.VISIBLE);
                }
                catch(Exception x){
                    x.printStackTrace();
                }


            }
        });
    }
}
