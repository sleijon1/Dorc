package com.example.dorc;

public class Gold {
    private int gold;
    private int timeGatheredGold;

    public Gold(){
        gold = 0;
    }

    //If we resume or if player hits orc we want to increase instantly
    public void increaseGold(long increaseAmount, boolean hitOrResume){
        if(hitOrResume){
            //call function for updating visual gold value for instant increase
            gold += increaseAmount;
        }else{
            timeGatheredGold += increaseAmount;
        }
    }

    public int getAmount(){
        return gold;
    }
}
