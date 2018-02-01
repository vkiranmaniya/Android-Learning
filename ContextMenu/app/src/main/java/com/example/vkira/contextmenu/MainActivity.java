package com.example.vkira.contextmenu;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView mList;
    ArrayAdapter adpater;
    ArrayList<String> mArrayList = new ArrayList<>();

    LinearLayout mLayout;
    EditText mEditText;
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = findViewById(R.id.addlayout);
        mEditText = findViewById(R.id.newitem);
        mButton = findViewById(R.id.addbutton);

        mList = findViewById(R.id.listView);
        registerForContextMenu(mList);

        mArrayList.add("Surat");
        mArrayList.add("Ahemdabad");
        mArrayList.add("Bharuch");
        mArrayList.add("Vapi");
        mArrayList.add("Navsari");

        adpater = new ArrayAdapter(MainActivity.this , R.layout.list_adapter_layout ,R.id.list_item , mArrayList);
        mList.setAdapter(adpater);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.contextmenu , menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete:
                mArrayList.remove(info.position);
                adpater.notifyDataSetChanged();
                break;
            case R.id.add:
                mLayout.setVisibility(View.VISIBLE);
                mButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view){
                        String x = mEditText.getText().toString();
                        mEditText.setText(null);
                        mArrayList.add(x);
                        adpater.notifyDataSetChanged();
                        mLayout.setVisibility(View.GONE);
                    }
                });
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putStringArrayList("DATA", mArrayList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mArrayList.clear();
        mArrayList.addAll(savedInstanceState.getStringArrayList("DATA"));
    }
}
