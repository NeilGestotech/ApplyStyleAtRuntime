package com.example.neil.myapplication;

/**
 * Created by neil on 8/18/15.
 */

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class MyButtonDrawable extends GradientDrawable {

    public MyButtonDrawable(SharedPreferences prefs){
        String borderWidth = prefs.getString(MainActivity.BORDER_WIDTH, "The string is not available!");
        String borderColor = prefs.getString(MainActivity.BORDER_COLOR, "The string is not available!");
        String bgColor = prefs.getString(MainActivity.BACKGROUND_COLOR, "The string is not available!");

        this.setColor(Color.parseColor(bgColor));
        //gd.setCornerRadius(5);
        this.setStroke(Integer.valueOf(borderWidth), Color.parseColor(borderColor));
    }
}