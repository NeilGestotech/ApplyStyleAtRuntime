package com.example.neil.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.Button;

/**
 * Created by neil on 8/19/15.
 */
public class MyButton extends Button {

    public MyButton(Context context, SharedPreferences prefs, String text) {
        super(context);
        setProperties(prefs, text);
    }

    private void setProperties(SharedPreferences prefs, String text){
        String bgColor = prefs.getString(MainActivity.BACKGROUND_COLOR, "The string is not available!");
        String textColor = prefs.getString(MainActivity.TEXT_COLOR, "The string is not available!");
        this.setBackgroundColor(Color.parseColor(bgColor));
        this.setTextColor(Color.parseColor(textColor));
        this.setText(text.toString());
    }
}
