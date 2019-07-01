package com.example.dorc;

public class Inventory {
    private int space;
    private Gear[] gears = new Gear[16];
    public int currentlyHeldItems = 0;

    public Inventory(int space){
        this.space = space;
    }

    public void putInInventory(Gear gear){
        if(currentlyHeldItems < 16) {
            gears[currentlyHeldItems] = gear;
            ++currentlyHeldItems;
        }
    }

    public Gear[] getInvArray(){
        return gears;
    }

    public int getTotalSpace(){
        return space;
    }

}
