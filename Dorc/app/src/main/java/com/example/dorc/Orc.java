package com.example.dorc;

import android.graphics.*;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.ProgressBar;


public class Orc {
    public int maxHealth;
    private int healthBar;
    private Bitmap image;
    private static final String TAG = "Orc.java";

    public Orc(Bitmap bmp){
        image = bmp;

        healthBar = 25;
        maxHealth = 25;
    }

    public void draw(Canvas canvas) {
        int centreX = ((canvas.getWidth() - 256) / 2) - 128; //256 is width of image
        int centreY = ((canvas.getHeight() - 256) / 2) - 128; //256 is height of image
        canvas.drawBitmap(image, centreX, centreY, null);
    }

    public void setHealthBar(int hp){
        healthBar = hp;
    }

    public int getHealthBar(){
        return healthBar;
    }

    //Will get a lot more complex with spells, dodges, gear etc.
    public int hit(double baseDmg){
        double percentDmg = baseDmg/maxHealth;
        double percentHealth = percentDmg*100;
        int rounded = (int) Math.round(percentHealth);
        this.healthBar = this.healthBar - (int) Math.round(baseDmg);

        return rounded;
    }
}

