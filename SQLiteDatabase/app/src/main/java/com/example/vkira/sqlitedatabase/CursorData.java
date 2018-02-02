package com.example.vkira.sqlitedatabase;

/**
 * Created by vkira on 25-01-2018.
 */

public class CursorData {
    int mId;
    String mUser , mPassword;

    public CursorData(int mId, String mUser, String mPassword) {
        this.mId = mId;
        this.mUser = mUser;
        this.mPassword = mPassword;
    }

    public int getmId() {
        return mId;
    }

    public String getmUser() {
        return mUser;
    }

    public String getmPassword() {
        return mPassword;
    }
}
