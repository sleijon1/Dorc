package com.example.dorc;

public class Gold {
    private int gold;

    public Gold(){
        gold = 0;
    }

    //If we resume or if player hits orc we want to increase instantly
    public void increaseGold(long increaseAmount){
        gold += increaseAmount;
    }

    public int getAmount(){
        return gold;
    }
}
