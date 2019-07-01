package com.example.dorc;

import android.graphics.*;


public class Orc {
    public int maxHealth;
    private int healthBar;
    private Bitmap image;
    private static final String TAG = "Orc.java";

    int legendaryWeight;
    int epicWeight;
    int rareWeight;
    int commonWeight;

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

    public Bitmap getImage(){
        return image;
    }

    public void setWeights(int legendaryWeight, int epicWeight, int rareWeight, int commonWeight){
        this.legendaryWeight = legendaryWeight;
        this.epicWeight = epicWeight;
        this.rareWeight = rareWeight;
        this.commonWeight = commonWeight;
    }
}

