package com.example.dorc;

import android.graphics.Bitmap;

public class orcMage extends Orc{
    private int experience = 5;

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

    public int hit() {
        double dmg = 0;
        BasicWeapon playerWeapon = MainActivity.testPlayer.getItemSet().getWeapon();
        if (playerWeapon != null) {
            if (!playerWeapon.getStats().containsKey("mage_killer")) {
                dmg = super.getPlayerRawDmg();
            }else{
                //TODO this is silly
                dmg = 1000;
            }
        }
        return super.dealDamage(dmg);
    }

    public int getExperience(){
        return experience;
    }
}
