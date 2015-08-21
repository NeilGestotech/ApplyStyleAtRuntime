package com.example.neil.myapplication;

/**
 * Created by neil on 8/18/15.
 */
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;

public class MyEditTextDrawable extends ShapeDrawable {
    private float mLineWidth = 1f;
    private Paint mLinePaint;
    private int color;

    public MyEditTextDrawable(SharedPreferences prefs){

        String bgColor = prefs.getString(MainActivity.BACKGROUND_COLOR, "The string is not available!");
        setColor(Integer.valueOf(bgColor)); //set the main color for this drawable

        // setup the Paint for drawing the lines
        mLinePaint = new Paint();
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(mLineWidth);
        setProperties(prefs);
    }

    private void setProperties(SharedPreferences prefs){
        String borderWidth = prefs.getString(MainActivity.BORDER_WIDTH, "The string is not available!");
        String borderColor = prefs.getString(MainActivity.BORDER_COLOR, "The string is not available!");
        setLineColor(Color.parseColor(borderColor));
        setLineWidth(Float.valueOf(borderWidth));
    }

    public void setColor(int color) {
        Paint paint = getPaint();
        paint.setColor(color);

    }

    public void setLineColor(int color) {
        this.color = color;
    }

    public void setLineWidth(float lineWidth) {
        mLineWidth = lineWidth;
        mLinePaint.setStrokeWidth(mLineWidth);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // bottom black line
        // //////////////////
        mLinePaint.setColor(color);

        canvas.drawLine(getBounds().left, getBounds().bottom - mLineWidth
                * 0.5f, getBounds().right, getBounds().bottom - mLineWidth
                * 0.5f, mLinePaint);

        // top
        canvas.drawLine(getBounds().left, getBounds().top + mLineWidth * 0.5f,
                getBounds().right, getBounds().top + mLineWidth * 0.5f,
                mLinePaint);

        // left
        canvas.drawLine(getBounds().left + mLineWidth * 0.5f,
                getBounds().bottom , getBounds().left + mLineWidth
                        * 0.5f, getBounds().top + mLineWidth, mLinePaint);

        // right
        canvas.drawLine(getBounds().right - mLineWidth * 0.5f,
                getBounds().bottom , getBounds().right - mLineWidth
                        * 0.5f, getBounds().top + mLineWidth, mLinePaint);
    }
}