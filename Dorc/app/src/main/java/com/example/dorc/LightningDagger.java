package com.example.dorc;

public class LightningDagger extends BasicWeapon {
    public LightningDagger(){
        super(R.drawable.lightningdagger, "Dagger of Will I am");
        rarity = "COMMON";
        this.setStats("damage", 8);
        this.setStats("warrior_killer", 1);
        this.setStats("armor_pen", 4);
    }
}
