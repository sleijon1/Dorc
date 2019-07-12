package com.example.dorc;

import android.graphics.Bitmap;

public class orcRogue extends Orc{
    private int experience = 15;
    private double dodge = 0.5;
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

    public int hit(){
        double dmg = 0;
        BasicWeapon playerWeapon = MainActivity.testPlayer.getItemSet().getWeapon();
        if(playerWeapon != null){
            if(!playerWeapon.getStats().containsKey("dodge_immune")){
                if(Math.random() < dodge){
                    dmg = 0;
                }else{
                    dmg = super.getPlayerRawDmg();
                }
            }else{
                dmg = super.getPlayerRawDmg();
            }
        }
        return super.dealDamage(dmg);
    }

    public String onMiss(){
        return "DODGE";
    }

    public int getExperience(){
        return experience;
    }
}
