package com.example.dorc;

import android.graphics.Bitmap;

public class orcWarrior extends Orc {
    public orcWarrior(Bitmap bitmap){
        super(bitmap);
        setDropWeights();
    }

    private void setDropWeights(){
        legendaryWeight = 4;
        epicWeight = 3;
        rareWeight = 2;
        commonWeight = 1;

    }
}
