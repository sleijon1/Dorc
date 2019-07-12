package com.example.dorc;

//Class for keeping all information regarding a current player hit

public class PlayerHit {
    boolean hit;
    private int damage;
    private boolean finishingBlow;
    private Gear loot;
    private boolean miss;
    private String missMessage;

    // Can only be hit for now to simplify
    public PlayerHit(){
        hit = true;
    }

    public void setDamage(int dmg){
        this.damage = dmg ;
    }

    public void setDamage(String dmg){this.damage = dmg;}

    public int getDamage(){
        return this.damage;
    }

    public void setFinishingBlow(){
        finishingBlow = true;
    }

    public boolean getFinishingBlow(){
        return finishingBlow;
    }

    public void setLoot(Gear loot){
        this.loot = loot;
    }

    public Gear getLoot(){
        return this.loot;
    }

    public void setMiss(boolean miss){
        this.miss = miss;
    }

    public boolean getMiss(){
        return miss;
    }

    public String getMissMessage(){
        return missMessage;
    }

}
