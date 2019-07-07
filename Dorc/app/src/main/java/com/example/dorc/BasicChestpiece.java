package com.example.dorc;

import android.util.Log;

public class BasicChestpiece extends Gear {
    private static final String TAG = "BasicChestPiece";

    public BasicChestpiece(){
        super(R.drawable.chestplate1, "Basic Chestpiece");
        rarity = "EPIC";
        this.setStats("damage", 0);
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        Log.i(TAG, "in BasicChestPiece");
        return false;
    }

}
