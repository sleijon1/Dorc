package com.example.dorc;

public class SpearOfXargon extends BasicWeapon {
    public SpearOfXargon(){
        super(R.drawable.golden_spear, "Spear of Xargon");
        rarity = "LEGENDARY";
        this.setStats("damage", 10);
        this.setStats("mage_killer", 1);
    }
}
