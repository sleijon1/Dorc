package com.example.dorc;

public class SwordOfDoom extends BasicWeapon {
    public SwordOfDoom(){
        super(R.drawable.lightning_sword, "Oathbreaker");
        rarity = "EPIC";
        this.setStats("damage", 15);
        this.setStats("rogue_killer", 1);
        this.setStats("dodge_immune", 1);
    }
}
