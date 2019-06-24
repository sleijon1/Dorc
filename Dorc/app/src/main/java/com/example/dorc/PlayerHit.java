package com.example.dorc;

//Class for calculting what type of hit player gets on certain opponent
//does barely anything right now
public class PlayerHit {
    boolean hit;
    boolean crit;
    boolean dodge;
    boolean miss;
    private long lastMeasuredTime = 0;


    // Can only be hit for now to simplify
    public PlayerHit(){
        hit = true;
    }

    public long getLastMeasured() {
        return lastMeasuredTime;
    }

    public void setLastMeasured(long lastMeasuredTime) {
        this.lastMeasuredTime = lastMeasuredTime;
    }
}
