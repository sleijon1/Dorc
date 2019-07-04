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
    private String name;
    public String rarity;
    private ImageButton btn;

    public Gear(int dmg, int iconId, String name){
        damage = dmg;
        this.iconId = iconId;
        this.name = name;
    }

    public int getDamage(){
        return damage;
    }

    public int getIconId(){
        return iconId;
    }

    public String getName(){return name;
    }

    public String getRarity(){
        return rarity;
    }
}
