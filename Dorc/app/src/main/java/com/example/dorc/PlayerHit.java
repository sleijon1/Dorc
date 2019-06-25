package com.example.dorc;

//Class for keeping all information regarding a current player hit

public class PlayerHit {
    boolean hit;
    private int damage;
    private boolean finishingBlow;

    // Can only be hit for now to simplify
    public PlayerHit(){
        hit = true;
    }

    public void setDamage(int dmg){
        this.damage = dmg;
    }

    public int getDamage(){
        return this.damage;
    }

    public void setFinishingBlow(){
        finishingBlow = true;
    }

    public boolean getFinishingBlow(){
        return finishingBlow;
    }
}
