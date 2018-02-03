package com.example.vkira.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Intent I;
    EditText num1 ,num2;
    Button sumBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.fnumberet);
        num2 = findViewById(R.id.snumberet);
        sumBtn = findViewById(R.id.button);


        sumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int number1 = Integer.parseInt(num1.getText().toString());
                int number2 = Integer.parseInt(num2.getText().toString());
                int sum = number1+number2;
                I = new Intent(MainActivity.this , MultiplicationActivity.class);
                I.putExtra("add" , sum);
                startActivity(I);
            }
        });
    }
}
