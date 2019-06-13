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
                    canvas.drawBitmap(image, 50, 50, null);
                    Log.i(TAG, "Going out of drawing");

    }
    }

