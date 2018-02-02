package com.example.vkira.sqlitedatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifyDatabaseActivity extends AppCompatActivity {

    ListView mDataListView;
    AppDatabaseHelper helper = new AppDatabaseHelper(this);
    CustomAdapter adapter;
    Cursor mCursor;
    ArrayList<CursorData> Data = new ArrayList<>();

    LinearLayout mUpdateBox;
    EditText mEditName , mEditPassword;
    Button mUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_database);

        Intent i = getIntent();

        mDataListView = findViewById(R.id.dataListView);

        mEditName = findViewById(R.id.editName);
        mEditPassword = findViewById(R.id.editPassword);
        mUpdateBox = findViewById(R.id.UpdateBox);
        mUpdate = findViewById(R.id.updateData);
        showData();

    }

    public void showData(){
        mCursor = helper.getAllData();
        while(mCursor.moveToNext()){
            CursorData object = new CursorData(mCursor.getInt(0) , mCursor.getString(1) , mCursor.getString(2));
            Data.add(object);
        }
        adapter = new CustomAdapter(Data , ModifyDatabaseActivity.this);
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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition = info.position;
        switch (item.getItemId()){
            case R.id.delete:
                int x = deleteData(listPosition);
                adapter.notifyDataSetChanged();
                showDialog("Rows Deleted" , x+" Rows Deleted from Database");
                break;
            case R.id.update:
                CursorData mData = Data.get(listPosition);
                final Cursor cursor = helper.searchData(mData.getmUser());
                cursor.moveToFirst();
                String name = cursor.getString(1);
                String password = cursor.getString(2);
                mEditName.setText(name);
                mEditPassword.setText(password);
                mUpdateBox.setVisibility(View.VISIBLE);
                mUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String a = mEditName.getText().toString();
                        String b = mEditPassword.getText().toString();
                        int c = cursor.getInt(0);
                        int i = updateData(a,b,c);
                        showDialog("Update Successfull" ,i+" Rows Updated");
                        adapter.notifyDataSetChanged();
                        showData();
                    }
                });
                break;
            default:
                break;
        }
        return true;
    }

    public int deleteData(int pos){
        Data.remove(pos);
        CursorData mData = Data.get(pos);
        int id = mData.getmId();
        return helper.deleteData(id);
    }

    public int updateData(String name , String password , int id){
        return helper.updateData(name , password , id);
    }

    public void showDialog(String title , String Message){
        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(this);
        mAlertDialog.setTitle(title);
        mAlertDialog.setMessage(Message);
        mAlertDialog.setCancelable(false);
        String mYes = "OK";
        mAlertDialog.setPositiveButton(mYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Implement Action Here
            }
        });
        mAlertDialog.show();
    }
}
