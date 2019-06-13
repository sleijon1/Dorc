package com.example.dorc;

public class Player {
    private int lastMeasuredTime = 0;
    private Gold currentGold = new Gold();
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

    public int getLastMeasured(){
        return lastMeasuredTime;
    }

    // last measured time in milliseconds since start of epoch
    public void setLastMeasured(int time){
        lastMeasuredTime = time;
    }

}
