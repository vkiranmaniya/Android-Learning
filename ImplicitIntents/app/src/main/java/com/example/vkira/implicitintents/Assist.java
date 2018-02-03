package com.example.vkira.implicitintents;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by vkira on 12-01-2018.
 */

public class Assist {
    public static void makeToast(Context c , String toastMessage){
        Toast.makeText(c , toastMessage , Toast.LENGTH_LONG).show();
    }
}
