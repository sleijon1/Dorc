package com.example.dorc;

public class BasicWeapon extends Gear {
    public BasicWeapon(){
        super(5, R.drawable.firesword, "Basic Sword");
        rarity = "COMMON";
        this.setStats("damage", 5);
    }
}
