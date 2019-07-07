package com.example.dorc;

public class BasicOffhand extends Gear {
    public BasicOffhand(){
        super(R.drawable.offhand1, "Basic Shield");
        rarity = "LEGENDARY";
        this.setStats("damage", 0);
    }
}
