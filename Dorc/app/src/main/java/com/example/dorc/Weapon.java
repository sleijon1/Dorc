package com.example.dorc;

import android.media.Image;

public class Weapon {
    private int damage;
    private Image icon;

    public Weapon(int dmg, Image icon){
        damage = dmg;
        this.icon = icon;
    }

    public int getDamage(){
        return damage;
    }

    public Image getIcon(){
        return icon;
    }
}
