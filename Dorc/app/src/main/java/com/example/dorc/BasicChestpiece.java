package com.example.dorc;

import android.util.Log;

public class BasicChestpiece extends Gear {
    private static final String TAG = "BasicChestPiece";

    public BasicChestpiece(){
        super(0, R.drawable.chestplate1, "Basic Chestpiece");
        rarity = "EPIC";
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
