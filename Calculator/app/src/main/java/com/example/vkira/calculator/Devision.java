package com.example.vkira.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Devision extends AppCompatActivity {

    TextView result;
    EditText num2;
    Button sumBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devision);

        Intent I = getIntent();
        final int output = I.getIntExtra("mul", 1);
        result = findViewById(R.id.result);
        result.setText(output+" ");

        num2 = findViewById(R.id.snumberet);
        sumBtn = findViewById(R.id.additionbt);


        sumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number2 = Integer.parseInt(num2.getText().toString());
                try{
                    int div = output/number2;
                    Intent I = new Intent( Devision.this, SubtractActivity.class);
                    I.putExtra("div", div);
                    startActivity(I);
                }
                catch(Exception x){
                    x.printStackTrace();
                }


            }
        });
    }
}
