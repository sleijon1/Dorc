package com.example.dorc;

public class Gold {
    private int gold;

    public Gold(){
        gold = 0;
    }

    public void increaseGold(){
        gold += 1;
    }
    //Hit can be crit or regular hit
    public void increaseGold(int hit){
        gold += 20;
    }
}
