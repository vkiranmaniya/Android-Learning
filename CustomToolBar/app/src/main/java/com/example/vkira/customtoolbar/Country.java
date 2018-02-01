package com.example.vkira.customtoolbar;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by vkira on 17-01-2018.
 */

public class Country {
    private Integer mImage;
    private static ArrayList<Integer> mCountryImage = new ArrayList<>();

    public Country(Integer mImage) {
        this.mImage = mImage;
        mCountryImage.add(this.mImage);
    }

    public static ArrayList<Integer> getmCountryImage() {
        return mCountryImage;
    }
}
