package com.example.dorc;

import android.graphics.Bitmap;
import android.util.Log;

public class orcWarrior extends Orc {
    private static final String TAG = "orcWarrior Class";
    private double block = 0.8;
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

    public int hit(){
        double dmg = 0;
        BasicWeapon playerWeapon = MainActivity.testPlayer.getItemSet().getWeapon();
        if(playerWeapon != null){
            if(!playerWeapon.getStats().containsKey("armor_pen")){
                if(Math.random() < block){
                    dmg = 0;
                }else{
                    dmg = super.getPlayerRawDmg();
                }
            }else{
                double armorPen = playerWeapon.getStats().get("armor_pen");
                Log.i(TAG, "armrP " + armorPen);
                double armorPenPercent = armorPen/10;
                Log.i(TAG, "armrP " + armorPenPercent);
                if(armorPenPercent > block){
                    dmg = super.getPlayerRawDmg();
                }else {
                    if(Math.random() < (block - armorPenPercent)){
                        dmg = 0;
                    }else {
                        dmg = super.getPlayerRawDmg();
                    }
                }
            }
        }
        return super.dealDamage(dmg);
    }
}
