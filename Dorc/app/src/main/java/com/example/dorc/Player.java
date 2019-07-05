package com.example.dorc;

public class Player {
    private long lastMeasuredTime = 0;
    private Gold currentGold = new Gold();
    private Inventory playerInventory = new Inventory(16);
    private ItemSet itemSet = new ItemSet();
    //private Experience currentExperience;
    //private HashMap currentGear;
    public Player(){
     // Player is developed later. This is a class that should be instantiated when
     // a player connects with their google play account. The stats:gold, experience, gear etc.
     // should be gathered from a database when player logs in.
    }

    public Gold getGold(){
        return currentGold;
    }

    public long getLastMeasured(){
        return lastMeasuredTime;
    }

    // last measured time in milliseconds since start of epoch
    public void setLastMeasured(long time){
        lastMeasuredTime = time;
    }

    public Inventory getPlayerInventory(){
        return playerInventory;
    }

    public Gear getPlayerInventory(int index){
        return playerInventory.getInvArray(index);
    }

    public ItemSet getItemSet(){
        return itemSet;
    }

}
