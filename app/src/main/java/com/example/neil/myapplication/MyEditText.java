package com.example.neil.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by neil on 8/12/15.
 */
public class MyEditText extends EditText {

    public MyEditText(Context context, SharedPreferences sharedPreferences, String text){
        super(context);
        setProperties(sharedPreferences, text);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context) {
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
        this.setHintTextColor(Color.parseColor(textColor));
        this.setHint(text.toString());

    }
}
