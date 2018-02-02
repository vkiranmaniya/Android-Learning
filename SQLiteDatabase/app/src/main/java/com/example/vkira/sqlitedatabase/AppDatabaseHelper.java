package com.example.vkira.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by vkira on 24-01-2018.
 */

public class AppDatabaseHelper{

    DBHelper helper;
    ContentValues contentValues;

    public AppDatabaseHelper(Context context){
        helper = new DBHelper(context);
        contentValues = new ContentValues();
    }

    public long insertData(String name , String password){
        contentValues.put(DBHelper.UNAME , name);
        contentValues.put(DBHelper.PASSWORD , password);

        SQLiteDatabase DB = helper.getWritableDatabase();
        long id = DB.insert(DBHelper.TABLE_NAME , null , contentValues);
        return id;
    }

    public Cursor getAllData(){
        SQLiteDatabase DB = helper.getWritableDatabase();
        String[] columns = {DBHelper.UID, DBHelper.UNAME, DBHelper.PASSWORD};
        Cursor mCursor = DB.query(DBHelper.TABLE_NAME , columns, null , null , null , null , null);
        return mCursor;
    }

    public Cursor searchData(String name){
        /*SELECT * FROM TABLE WHERE name = ' ' or password = ' '*/
        SQLiteDatabase DB = helper.getWritableDatabase();
        String[] columns = {DBHelper.UID, DBHelper.UNAME, DBHelper.PASSWORD};
        Cursor mCursor = DB.query(DBHelper.TABLE_NAME , columns, DBHelper.UNAME+" = '"+name+"'" , null , null , null , null);
        return mCursor;
    }

    public int deleteData(int userId){
        SQLiteDatabase DB = helper.getWritableDatabase();
        return DB.delete(DBHelper.TABLE_NAME , DBHelper.UID +"= "+userId , null);
    }

    public int updateData(String name , String password , int id){
        SQLiteDatabase DB = helper.getWritableDatabase();
        contentValues.put(DBHelper.UNAME , name);
        contentValues.put(DBHelper.PASSWORD , password);
        return DB.update(DBHelper.TABLE_NAME ,contentValues , DBHelper.UID+" = "+id , null);
    }

    /*inner class for database open helper to made private data accessible to outer class*/
    static class DBHelper extends SQLiteOpenHelper{
        private static final String DB_NAME = "FChatDB";
        static final String TABLE_NAME = "Users";
        private static final String UID = "_id";
        private static final String UNAME = "user_name";
        private static final String PASSWORD = "password";
        private static final int DB_VER = 1;
        private Context mContext;

        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+UNAME+" VARCHAR(255) ,"+PASSWORD+" VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXIST "+TABLE_NAME;

        public DBHelper(Context context) {
            super(context, DB_NAME, null , DB_VER);
            this.mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase DB) {
            try{
                DB.execSQL(CREATE_TABLE);
                Toast.makeText(mContext, "DB:onCreate Called", Toast.LENGTH_SHORT).show();
            }
            catch(SQLException x){
                x.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
            try{
                DB.execSQL(DROP_TABLE);
                onCreate(DB);
                Toast.makeText(mContext, "DB:onUpgrade Called", Toast.LENGTH_SHORT).show();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }
    }

}
