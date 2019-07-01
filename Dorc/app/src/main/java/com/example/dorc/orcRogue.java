package com.example.dorc;

import android.graphics.Bitmap;

public class orcRogue extends Orc{
    public orcRogue(Bitmap bitmap){
        super(bitmap);
        setDropWeights();
    }

    private void setDropWeights(){
        legendaryWeight = 1;
        epicWeight = 2;
        rareWeight = 3;
        commonWeight = 4;

    }
}
