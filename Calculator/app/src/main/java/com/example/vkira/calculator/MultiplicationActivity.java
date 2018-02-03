package com.example.vkira.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MultiplicationActivity extends AppCompatActivity {

    TextView result;
    EditText num2;
    Button sumBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        final int output = getIntent().getIntExtra("add",0);
        result = findViewById(R.id.result);
        result.setText(output+" ");

        num2 = findViewById(R.id.mulnum2);
        sumBtn = findViewById(R.id.mulbutton);


        sumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int number2 = Integer.parseInt(num2.getText().toString());
                int mul = output * number2;
                Intent In = new Intent(MultiplicationActivity.this, Devision.class);
                In.putExtra("mul", mul);
                startActivity(In);
            }
        });

    }
}
