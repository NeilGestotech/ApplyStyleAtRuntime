package com.example.neil.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by neil on 8/12/15.
 */
public class MyTextView extends TextView {

    public MyTextView(Context context, SharedPreferences sharedPreferences, String text){
        super(context);
        setProperties(sharedPreferences, text);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        /*if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font.ttf");
            setTypeface(tf);
        }*/
    }

    private void setProperties(SharedPreferences prefs, String text){
        String textColor = prefs.getString(MainActivity.TEXT_COLOR, "The string is not available!");
        this.setTextColor(Color.parseColor(textColor));
        this.setText(text.toString());
    }
}
