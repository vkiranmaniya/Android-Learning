package com.example.vkira.sqlitedatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    Button mSearchButton;
    EditText mSearchBox;
    ListView mDataListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent i = getIntent();

        mSearchButton = findViewById(R.id.searchbutton);
        mSearchBox = findViewById(R.id.searchBox);
        mDataListView = findViewById(R.id.dataListView);

        final AppDatabaseHelper helper = new AppDatabaseHelper(this);
        final ArrayList<CursorData> Data = new ArrayList<>();

        final CustomAdapter adapter = new CustomAdapter(Data , SearchActivity.this);
        mDataListView.setAdapter(adapter);


        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor mCursor = helper.searchData(mSearchBox.getText().toString());
                while(mCursor.moveToNext()){
                    CursorData object = new CursorData(mCursor.getInt(0) , mCursor.getString(1) , mCursor.getString(2));
                    Data.add(object);
                }
                adapter.notifyDataSetChanged();
            }
        });


    }
}
