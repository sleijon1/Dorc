package com.example.dorc;

public class BasicWeapon extends Gear {
    public BasicWeapon(){
        super(R.drawable.firesword, "Basic Sword");
        rarity = "COMMON";
        this.setStats("damage", 5);
    }

    public BasicWeapon(int iconId, String name){
        super(iconId, name);
    }
}
