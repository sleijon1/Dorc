package com.example.dorc;

//Class for keep all information regarding a current player hit

public class PlayerHit {
    boolean hit;
    boolean crit;
    boolean dodge;
    boolean miss;
    private long lastMeasuredTime = 0;
    private Orc currentOrc;

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

    public void setCurrentOrc(Orc orc){
        currentOrc = orc;
    }

    public Orc getCurrentOrc(){
        return currentOrc;
    }

}
