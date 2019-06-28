package com.example.dorc;

public class Inventory {
    private int space;
    private Weapon[] weapons = new Weapon[5];
    private int currentlyHeldItems = 0;

    public Inventory(int space){
        this.space = space;
    }

    public void putInInventory(Weapon weapon){
        weapons[currentlyHeldItems] = weapon;
        ++currentlyHeldItems;
    }

}
