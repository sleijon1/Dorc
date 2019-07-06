package com.example.dorc;

import java.util.ArrayList;

public class Inventory {
    private int space;
    private ArrayList<Gear> gears = new ArrayList<>(16);
    public int currentlyHeldItems = 0;

    public Inventory(int space){
        this.space = space;
    }

    public void putInInventory(Gear gear){
        if(currentlyHeldItems < 16) {
            gears.add(gear);
            currentlyHeldItems = gears.size();
        }
    }

    public ArrayList<Gear> getInvArray(){
        return gears;
    }

    public Gear getInvArray(int index){
        return gears.get(index);
    }

    public void removeItem(Gear gear){
        gears.remove(gear);
        currentlyHeldItems = gears.size();
    }

    public int getTotalSpace(){
        return space;
    }

}
