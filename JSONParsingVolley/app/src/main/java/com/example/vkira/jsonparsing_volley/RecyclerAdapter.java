package com.example.vkira.jsonparsing_volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by vkira on 07-02-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private Context mContext;
    private ArrayList<ActorsModel> mActorData;

    public RecyclerAdapter(Context mContext, ArrayList<ActorsModel> mActorData) {
        this.mContext = mContext;
        this.mActorData = mActorData;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater infaltor = LayoutInflater.from(mContext);
        View view = infaltor.inflate(R.layout.actors_row_layout , null);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int i) {
        holder.actorName.setText(mActorData.get(i).getName());
        holder.actorDOB.setText("DOB : "+mActorData.get(i).getDob());
        holder.actorChild.setText("Children : "+mActorData.get(i).getChildren());
        holder.actorCountry.setText("Country : "+mActorData.get(i).getCountry());
        holder.actorHeight.setText("Height : "+mActorData.get(i).getHeight());
        holder.actorWife.setText("Spouce : "+mActorData.get(i).getSpouse());
        holder.actorDesc.setText("About : "+mActorData.get(i).getDescription());
        new DownloadImageTask(holder.actorImage).execute(mActorData.get(i).getImage());
    }

    @Override
    public int getItemCount() {
        return mActorData.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView actorImage;
        TextView actorName , actorDOB , actorHeight , actorCountry , actorWife , actorChild , actorDesc;
        public RecyclerViewHolder(View view) {
            super(view);
            actorImage = view.findViewById(R.id.actor_image);
            actorName = view.findViewById(R.id.actor_name);
            actorDOB = view.findViewById(R.id.actor_dob);
            actorHeight = view.findViewById(R.id.actor_height);
            actorCountry = view.findViewById(R.id.actor_country);
            actorWife = view.findViewById(R.id.actor_wife);
            actorChild = view.findViewById(R.id.actor_child);
            actorDesc = view.findViewById(R.id.actor_desc);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
