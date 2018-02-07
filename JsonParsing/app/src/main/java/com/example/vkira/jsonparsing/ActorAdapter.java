package com.example.vkira.jsonparsing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by vkira on 03-02-2018.
 */

public class ActorAdapter extends ArrayAdapter<ActorsModel> {

    ArrayList<ActorsModel> mArrayList;
    Context mContext ;
    int mResources;

    LayoutInflater inflater;
    public ActorAdapter(@NonNull Context context, int layoutResourceId, @NonNull ArrayList<ActorsModel> objects) {
        super(context , layoutResourceId , objects);
        mArrayList = objects;
        mContext = context;
        mResources = layoutResourceId;
        inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(mResources , null);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.actor_name);
            holder.description = convertView.findViewById(R.id.actor_desc);
            holder.children = convertView.findViewById(R.id.actor_child);
            holder.country = convertView.findViewById(R.id.actor_country);
            holder.dob = convertView.findViewById(R.id.actor_dob);
            holder.height = convertView.findViewById(R.id.actor_height);
            holder.spouse = convertView.findViewById(R.id.actor_wife);
            holder.image = convertView.findViewById(R.id.actor_image);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(mArrayList.get(position).getName());
        holder.dob.setText(mArrayList.get(position).getDob());
        holder.spouse.setText(mArrayList.get(position).getSpouse());
        holder.height.setText(mArrayList.get(position).getHeight());
        holder.country.setText(mArrayList.get(position).getCountry());
        holder.description.setText(mArrayList.get(position).getDescription());
        holder.children.setText(mArrayList.get(position).getChildren());
        new DownloadImageTask(holder.image).execute(mArrayList.get(position).getImage());
        return convertView;
    }

    static class ViewHolder{
        public TextView name;
        public TextView description;
        public TextView dob;
        public TextView country;
        public TextView height;
        public TextView spouse;
        public TextView children;
        public ImageView image;
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
