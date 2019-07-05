package com.example.dorc;

import android.view.View;

public class CustomOnClickListener implements View.OnClickListener {
    Gear toDisplay;

    public CustomOnClickListener(Gear gear){
        toDisplay = gear;
    }
    @Override
    public void onClick(View view){

    }
}
