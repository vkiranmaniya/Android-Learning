package com.example.vkira.customtoolbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by vkira on 17-01-2018.
 */

public class CountryRecycler extends RecyclerView.Adapter<CountryRecycler.CountryHolder> {

    private ArrayList<Integer> mImageList = new ArrayList<>();
    Context mContext;

    public CountryRecycler(Context mContext, ArrayList<Integer> mImageList) {
        this.mImageList = mImageList;
        this.mContext = mContext;
    }

    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycler , null);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryHolder holder, int position) {
        holder.mImageView.setImageResource(mImageList.get(position));
        holder.mImageView.setId(position);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    class CountryHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public CountryHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.country_image);
        }
    }
}
