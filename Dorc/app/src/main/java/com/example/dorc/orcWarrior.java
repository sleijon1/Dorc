package com.example.dorc;

import android.graphics.Bitmap;

public class orcWarrior extends Orc {
    private double block = 0.5;
    public orcWarrior(Bitmap bitmap){
        super(bitmap);
        setDropWeights();
        maxHealth = 100;
        setHealthBar(100);
    }

    private void setDropWeights(){
        legendaryWeight = 4;
        epicWeight = 3;
        rareWeight = 2;
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
