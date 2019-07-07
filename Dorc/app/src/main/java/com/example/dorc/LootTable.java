package com.example.dorc;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class LootTable {
    private static BasicWeapon basicWeapon = new BasicWeapon();
    private static BasicChestpiece basicChestpiece = new BasicChestpiece();
    private static BasicOffhand basicOffhand = new BasicOffhand();
    private static BasicHelmet basicHelmet = new BasicHelmet();

    private ArrayList<Gear> rareGear = new ArrayList<Gear>(1);
    private ArrayList<Gear> legendaryGear = new ArrayList<Gear>(1);
    private ArrayList<Gear> epicGear = new ArrayList<Gear>(1);
    private ArrayList<Gear> commonGear = new ArrayList<Gear>(1);

    private LinkedList<String> linkedList = new LinkedList<String>();


    public LootTable(Orc orc) {
            // TODO Gear-handling
            // This needs to be changed in the future to be dynamically added somehow
            // So that new items are handled effectively without digging in this code.
            // Possible Component system from unity...
            commonGear.add(basicWeapon);
            legendaryGear.add(basicOffhand);
            rareGear.add(basicHelmet);
            epicGear.add(basicChestpiece);

            addWeight(orc.legendaryWeight, "legendary");
            addWeight(orc.epicWeight, "epic");
            addWeight(orc.rareWeight, "rare");
            addWeight(orc.commonWeight, "common");
    }

    private void addWeight(int weight, String rarity){
        for(int i = 0; i<weight; i++){
            linkedList.add(rarity);
        }
    }

    public Gear calculateLoot(){
        int amountOfGear;
        Gear loot;
        double roll = Math.random();
        double rollRound = roll*10;
        rollRound = Math.round(rollRound);

        if(rollRound > 0){
            rollRound -= 1;
        }
        Log.i(TAG, "rollROUND:" + rollRound + "linked SIZE:" + linkedList.size());

        String rarity = linkedList.get((int)rollRound);

        switch(rarity){
            case "legendary":
                amountOfGear = legendaryGear.size();
                loot = legendaryGear.get(new Random().nextInt(amountOfGear));
                break;
            case "epic":
                amountOfGear = epicGear.size();
                loot = epicGear.get(new Random().nextInt(amountOfGear));
                break;
            case "rare":
                amountOfGear = rareGear.size();
                loot = rareGear.get(new Random().nextInt(amountOfGear));
                break;
            case "common":
                 amountOfGear = commonGear.size();
                 loot = commonGear.get(new Random().nextInt(amountOfGear));
                break;

                default:
                 loot = null;
        }
        return loot;
    }

}
