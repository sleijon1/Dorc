package com.example.dorc;

import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import static android.content.ContentValues.TAG;

public class Gear {
    private int damage;
    private int iconId;
    public String rarity;
    private ImageButton btn;

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
