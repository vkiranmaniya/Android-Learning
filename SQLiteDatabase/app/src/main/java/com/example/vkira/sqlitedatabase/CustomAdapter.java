package com.example.vkira.sqlitedatabase;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vkira on 25-01-2018.
 */

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<CursorData> mData = new ArrayList<>();


    public CustomAdapter(ArrayList<CursorData> mData, Context mContext) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView mId , mUser , mPassword;
        LayoutInflater infaltor = LayoutInflater.from(mContext);
        View v = infaltor.inflate(R.layout.sql_data_list , null);

        mId = v.findViewById(R.id.table_id);
        mUser = v.findViewById(R.id.table_name);
        mPassword = v.findViewById(R.id.table_password);

        mId.setText(String.valueOf(mData.get(i).getmId()));
        mUser.setText(mData.get(i).getmUser());
        mPassword.setText(mData.get(i).getmPassword());
        return v;
    }
}
