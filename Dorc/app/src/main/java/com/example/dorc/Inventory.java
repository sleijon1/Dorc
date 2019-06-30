package com.example.dorc;

public class Inventory {
    private int space;
    private Weapon[] weapons = new Weapon[16];
    public int currentlyHeldItems = 0;

    public Inventory(int space){
        this.space = space;
    }

    public void putInInventory(Weapon weapon){
        if(currentlyHeldItems < 16) {
            weapons[currentlyHeldItems] = weapon;
            ++currentlyHeldItems;
        }
    }

    public Weapon[] getInvArray(){
        return weapons;
    }

}
