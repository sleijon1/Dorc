package com.example.dorc;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Gear {
    private int damage;
    private int iconId;
    public String rarity;

    public Gear(int dmg, int iconId){
        damage = dmg;
        this.iconId = iconId;
    }

    public int getDamage(){
        return damage;
    }

    public int getIconId(){
        return iconId;
    }
}
