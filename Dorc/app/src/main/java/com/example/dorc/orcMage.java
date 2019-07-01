package com.example.dorc;

import android.graphics.Bitmap;

public class orcMage extends Orc{
    public orcMage(Bitmap bitmap){
        super(bitmap);
        setDropWeights();
    }

    private void setDropWeights(){
        legendaryWeight = 2;
        epicWeight = 3;
        rareWeight = 4;
        commonWeight = 1;

    }
}
