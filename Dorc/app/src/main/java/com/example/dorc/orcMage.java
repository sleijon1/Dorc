package com.example.dorc;

import android.graphics.Bitmap;

public class orcMage extends Orc{
    public orcMage(Bitmap bitmap){
        super(bitmap);
        setDropWeights();
        maxHealth = 25;
        setHealthBar(25);
    }

    private void setDropWeights(){
        legendaryWeight = 2;
        epicWeight = 3;
        rareWeight = 4;
        commonWeight = 1;

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
