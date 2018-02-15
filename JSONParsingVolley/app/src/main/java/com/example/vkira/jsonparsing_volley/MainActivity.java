package com.example.vkira.jsonparsing_volley;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vkira.jsonparsing_volley.networking.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ImageView mBannerImage;
    ArrayList<ActorsModel> mActorsModel;
    private static String SERVER_URL = "http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";
    private static String BANNER_URL = "https://www.androidhive.info/wp-content/uploads/2014/04/android-volley-library.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBannerImage = findViewById(R.id.banner_image);

        mActorsModel = new ArrayList<>();

        //Cache mCache = new DiskBasedCache(getCacheDir(),2048*1024);
        //Network mNetwork = new BasicNetwork(new HurlStack());
        //final RequestQueue mQueue = new RequestQueue(mCache , mNetwork);
        //mQueue.start();



        StringRequest mReq = new StringRequest(Request.Method.POST, SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            JSONArray jsonArray = jObj.getJSONArray("actors");
                            for(int i = 0 ; i < jsonArray.length() ; i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                ActorsModel model = new ActorsModel();
                                model.setName(object.getString("name"));
                                model.setDob(object.getString("dob"));
                                model.setCountry(object.getString("country"));
                                model.setChildren(object.getString("children"));
                                model.setDescription(object.getString("description"));
                                model.setImage(object.getString("image"));
                                model.setSpouse(object.getString("spouse"));
                                model.setHeight(object.getString("height"));
                                mActorsModel.add(model);
                                Log.d(i+"th Data Recived", model.toString());
                                //mQueue.stop();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error-Volly", "onErrorResponse: Error Fatching Json Data");
                    }
                });
        //mQueue.add(mReq);

        ImageRequest mImageReq = new ImageRequest(BANNER_URL,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        mBannerImage.setImageBitmap(response);
                    }
                },0, 0, ImageView.ScaleType.CENTER , null ,new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("BannerImage Error", "Error Loading Image:");
                    }
                });

        JsonObjectRequest mJsonReq = new JsonObjectRequest(Request.Method.POST, SERVER_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        /*Using Singleton object of RequestQueue in Entire Application*/
        Singleton.getInstance(getApplicationContext()).addToRequestQueue(mReq);
        Singleton.getInstance(getApplicationContext()).addToRequestQueue(mImageReq);
    }
}
