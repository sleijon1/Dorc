package com.example.dorc;

import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Gear {
    private int iconId;
    private String name;
    public String rarity;
    private ImageButton btn;
    private HashMap<String, Integer> stats = new HashMap<>();

    public Gear(int iconId, String name){
        this.iconId = iconId;
        this.name = name;
    }

    public int getIconId(){
        return iconId;
    }

    public String getName(){return name;
    }

    public String getRarity(){
        return rarity;
    }

    public void setStats(String statName, Integer value){
        stats.put(statName, value);
    }

    public Map<String, Integer> getStats(){
        return stats;
    }
}
