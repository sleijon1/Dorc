package com.example.dorc;

import android.graphics.Bitmap;

public class orcRogue extends Orc{
    public orcRogue(Bitmap bitmap){
        super(bitmap);
        setDropWeights();
        maxHealth = 75;
        setHealthBar(75);
    }

    private void setDropWeights(){
        legendaryWeight = 1;
        epicWeight = 2;
        rareWeight = 3;
        commonWeight = 4;
    }

    public int hit(double dmg){
        BasicWeapon playerWeapon = MainActivity.testPlayer.getItemSet().getWeapon();
        if(playerWeapon != null){
            Integer rawDamage = playerWeapon.getStats().get("damage");
            if(rawDamage != null){
                dmg = dmg + rawDamage;
            }
        }
        return super.hit(dmg);
    }
}
