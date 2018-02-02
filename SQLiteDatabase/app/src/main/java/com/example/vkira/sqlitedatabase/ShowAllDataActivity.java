package com.example.vkira.sqlitedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowAllDataActivity extends AppCompatActivity {

    ListView mDataListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_data);

        Intent i = getIntent();

        mDataListView = findViewById(R.id.dataListView);

        final AppDatabaseHelper helper = new AppDatabaseHelper(this);
        Cursor mCursor = helper.getAllData();
        ArrayList<CursorData> Data = new ArrayList<>();
        while(mCursor.moveToNext()){
            CursorData object = new CursorData(mCursor.getInt(0) , mCursor.getString(1) , mCursor.getString(2));
            Data.add(object);
        }
        final CustomAdapter adapter = new CustomAdapter(Data , ShowAllDataActivity.this);
        mDataListView.setAdapter(adapter);
        registerForContextMenu(mDataListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu , menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update:
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
