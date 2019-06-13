package com.example.dorc;

import android.graphics.*;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.SurfaceHolder;


public class Orc {
    private Bitmap image;
    private static final String TAG = "Orc.java";

    public Orc(Bitmap bmp){
        image = bmp;
    }

    public void draw(Canvas canvas) {
        Log.i(TAG, "Going into drawing");
        int centreX = ((canvas.getWidth() - 256) / 2) + 128; //256 is width of image
        int centreY = ((canvas.getHeight() - 256) / 2) - 128; //256 is height of image
        canvas.drawBitmap(image, -40, centreY, null);
        Log.i(TAG, "Going out of drawing");
    }
}

