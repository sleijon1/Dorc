package com.example.dorc;

//Class for keeping all information regarding a current player hit

public class PlayerHit {
    private int damage;
    private boolean finishingBlow;
    private Gear loot;
    private boolean miss;
    private String missMessage;
    private int experienceGained;

    // Can only be hit for now to simplify
    public PlayerHit(){
    }


    public void setExperienceGained(int experience){
        experienceGained = experience;
    }

    public int getExperienceGained(){
        return experienceGained;
    }
    public void setDamage(int dmg){
        this.damage = dmg ;
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

    public void setMissMessage(String missMessage){
        this.missMessage = missMessage;
    }

    public String getMissMessage(){
        return missMessage;
    }

}
