package com.example.dorc;

import android.graphics.Bitmap;

public class orcWarrior extends Orc {
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
                int armorPen = playerWeapon.getStats().get("armor_pen");
                int armorPenPercent = armorPen/10;

                if(armorPenPercent > block){
                    dmg = super.getPlayerRawDmg();
                }else {
                    if(Math.random() < block - armorPenPercent){
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
