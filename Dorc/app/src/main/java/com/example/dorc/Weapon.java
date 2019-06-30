package com.example.dorc;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Weapon {
    private int damage;
    private int iconId;

    public Weapon(int dmg, int iconId){
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
