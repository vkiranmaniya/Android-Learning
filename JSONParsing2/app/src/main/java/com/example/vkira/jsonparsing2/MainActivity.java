package com.example.vkira.jsonparsing2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    HttpURLConnection mConnection;
    StringBuffer mJsonBuffer;
    BufferedReader mReader;

    JSONObject mJson;
    JSONArray mJsonArray;

    ArrayList<ActorsModel> mActorDataList;


    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mRecyclerLayoutManager;
    Context mContext;
    RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.ActorRecyclerView);
        mContext = getApplicationContext();
        mRecyclerLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mRecyclerLayoutManager);

        mActorDataList = new ArrayList<ActorsModel>();
        new JSONLoaderAsyncTask().execute("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors");

    }

    class JSONLoaderAsyncTask extends AsyncTask<String , String , String>{

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL mUrl = new URL(urls[0]);
                mConnection = (HttpURLConnection) mUrl.openConnection();
                InputStream mStream = mConnection.getInputStream();
                mReader = new BufferedReader(new InputStreamReader(mStream));

                mJsonBuffer = new StringBuffer();
                String line = "";
                while((line = mReader.readLine()) != null){
                    mJsonBuffer.append(line);
                }
                return mJsonBuffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(mConnection != null){
                    mConnection.disconnect();
                }
                if (mReader != null){
                    try {
                        mReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("JSON Data", result);
            try {
                mJson = new JSONObject(result);
                mJsonArray = mJson.getJSONArray("actors");
                for(int i = 0 ; i < mJsonArray.length() ; i++){
                    JSONObject jActorObj = mJsonArray.getJSONObject(i);

                    ActorsModel model = new ActorsModel();
                    model.setName(jActorObj.getString("name"));
                    model.setDob(jActorObj.getString("dob"));
                    model.setCountry(jActorObj.getString("country"));
                    model.setChildren(jActorObj.getString("children"));
                    model.setDescription(jActorObj.getString("description"));
                    model.setImage(jActorObj.getString("image"));
                    model.setSpouse(jActorObj.getString("spouse"));
                    model.setHeight(jActorObj.getString("height"));

                    mActorDataList.add(model);
                }

                mAdapter = new RecyclerAdapter(mContext , mActorDataList);
                mRecyclerView.setAdapter(mAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
