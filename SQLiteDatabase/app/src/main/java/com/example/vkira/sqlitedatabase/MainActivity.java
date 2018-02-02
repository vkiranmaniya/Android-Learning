package com.example.vkira.sqlitedatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button mAddData , mShowData ,mSearchData , mModifyData;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowData = findViewById(R.id.showAllData);
        mAddData = findViewById(R.id.addData);
        mSearchData = findViewById(R.id.searchData);
        mModifyData = findViewById(R.id.modifyData);
        animation = AnimationUtils.loadAnimation(MainActivity.this , R.anim.main_animation);

        mShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShowData.startAnimation(animation);
                Intent i = new Intent(MainActivity.this , ShowAllDataActivity.class);
                startActivity(i);
            }
        });

        mAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , AddDataActivity.class);
                startActivity(i);
            }
        });

        mSearchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , SearchActivity.class);
                startActivity(i);
            }
        });

        mModifyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , ModifyDatabaseActivity.class);
                startActivity(i);
            }
        });

    }
}
