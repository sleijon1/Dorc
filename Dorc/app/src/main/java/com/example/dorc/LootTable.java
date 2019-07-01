package com.example.dorc;

import android.util.Log;

import java.util.LinkedList;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class LootTable {
    private static BasicSword basicSword = new BasicSword();
    private static BasicChestpiece basicChestpiece = new BasicChestpiece();
    private static BasicShield basicShield = new BasicShield();
    private static BasicHelmet basicHelmet = new BasicHelmet();

    private Gear[] rareGear = new Gear[1];
    private Gear[] legendaryGear = new Gear[1];
    private Gear[] epicGear = new Gear[1];
    private Gear[] commonGear = new Gear[1];

    private LinkedList<String> linkedList = new LinkedList<String>();


    public LootTable(Orc orc) {
            // TODO Gear-handling
            // This needs to be changed in the future to be dynamically added somehow
            // So that new items are handled effectively without digging in this code.
            // Possible Component system from unity...
            commonGear[0] = basicSword;
            legendaryGear[0] = basicShield;
            rareGear[0] = basicHelmet;
            epicGear[0] = basicChestpiece;

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
                amountOfGear = legendaryGear.length;
                loot = legendaryGear[new Random().nextInt(amountOfGear)];
                break;
            case "epic":
                amountOfGear = epicGear.length;
                loot = epicGear[new Random().nextInt(amountOfGear)];
                break;
            case "rare":
                amountOfGear = rareGear.length;
                loot = rareGear[new Random().nextInt(amountOfGear)];
                break;
            case "common":
                 amountOfGear = commonGear.length;
                 loot = commonGear[new Random().nextInt(amountOfGear)];
                break;

                default:
                 loot = null;
        }
        return loot;
    }

}
