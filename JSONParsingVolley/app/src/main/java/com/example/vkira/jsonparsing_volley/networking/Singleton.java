package com.example.vkira.jsonparsing_volley.networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by vkira on 07-02-2018.
 */

public class Singleton {
    private static Singleton mSingleton;
    private static RequestQueue mRequestQueue;
    private static Context mContext;

    public Singleton(Context mContext) {
        this.mContext = mContext;
        mRequestQueue = getmRequestQueue();
    }

    public RequestQueue getmRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized Singleton getInstance(Context context){
        if(mSingleton == null){
            mSingleton = new Singleton(context);
        }
        return mSingleton;
    }

    public<T> void addToRequestQueue(Request<T> request){
        mRequestQueue.add(request);
    }
}
