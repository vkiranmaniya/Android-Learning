package com.example.vkira.sqlitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {

    Button mOkButton;
    EditText mAddUser , mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        Intent i = getIntent();

        mOkButton = findViewById(R.id.ok);

        mAddUser = findViewById(R.id.addName);
        mPassword = findViewById(R.id.addPassword);

        final AppDatabaseHelper helper = new AppDatabaseHelper(this);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long i = helper.insertData( mAddUser.getText().toString() ,  mPassword.getText().toString());
                if(i < 0){
                    Toast.makeText(AddDataActivity.this, "Failed insert", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddDataActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
