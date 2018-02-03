package com.example.vkira.jsonparsing;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Entity;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView mActorsList;
    ActorAdapter mActorsAdapter;
    ArrayList<ActorsModel> mActorsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActorsList = findViewById(R.id.actor_list);
        mActorsData = new ArrayList<ActorsModel>();
    }

    public class ActorAsyncTask extends AsyncTask<String , Void , Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(params[0]);
            try {
                HttpResponse response = client.execute(post);
                int responseStatus = response.getStatusLine().getStatusCode();
                if(responseStatus == 200){
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    JSONObject jObj = new JSONObject(data);
                    JSONArray jArray = jObj.getJSONArray("actors");
                    for(int i = 0 ; i < jArray.length() ; i++){
                        JSONObject jActorObj = jArray.getJSONObject(i);

                        ActorsModel model = new ActorsModel();
                        model.setName(jActorObj.getString("name"));
                        model.setDob(jActorObj.getString("dob"));
                        model.setCountry(jActorObj.getString("country"));
                        model.setChildren(jActorObj.getString("children"));
                        model.setDescription(jActorObj.getString("description"));
                        model.setImage(jActorObj.getString("image"));
                        model.setSpouse(jActorObj.getString("spouse"));
                        model.setHeight(jActorObj.getString("height"));

                        mActorsData.add(model);
                    }
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(boolean result) {
            if (result == false){

            }
            else{
                mActorsAdapter = new ActorAdapter(MainActivity.this , R.layout.actors_row_layout , mActorsData);
                mActorsList.setAdapter(mActorsAdapter);

            }
        }
    }
}
