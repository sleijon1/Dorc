package com.example.dorc;

import java.util.ArrayList;

public class ItemSet {
    private BasicHelmet helmet;
    private BasicChestpiece chestpiece;
    private BasicWeapon weapon;
    private BasicOffhand offhand;

    private int dmgBonus;

    public ItemSet(){
        helmet = new BasicHelmet();
        chestpiece = new BasicChestpiece();
        weapon = new BasicWeapon();
        offhand = new BasicOffhand();
    }

    private void calculateBonuses(){
        //If helmet and chestpiece are of same sort
        //Add some stat etc.
    }

    // Sets new item @gear to current item
    // returns old item if exist else null
    public Gear setGear(Gear gear){
    //Add item to list and update the
    // the equivalent item
        if(gear instanceof BasicHelmet){
            BasicHelmet oldHelmet = helmet;
            helmet = (BasicHelmet)gear;
            return oldHelmet;
        }else if(gear instanceof BasicChestpiece){
            BasicChestpiece oldChestPiece = chestpiece;
            chestpiece = (BasicChestpiece)gear;
            return oldChestPiece;
        }else if(gear instanceof BasicOffhand){
            BasicOffhand oldOffhand = offhand;
            offhand = (BasicOffhand)gear;
            return oldOffhand;
        }else{
            BasicWeapon oldWeapon = weapon;
            weapon = (BasicWeapon)gear;
            return oldWeapon;
        }
    }

    public void setGear(BasicHelmet gear){
        helmet = gear;
    }

    public void setGear(BasicChestpiece gear){
        chestpiece = gear;
    }

    public void setGear(BasicWeapon gear){
        weapon = gear;
    }

    public void setGear(BasicOffhand gear){
        offhand = gear;
    }

    public BasicHelmet getHelmet(){
        return helmet;
    }

    public BasicChestpiece getChestpiece(){
        return chestpiece;
    }

    public BasicWeapon getWeapon(){
        return weapon;
    }

    public BasicOffhand getOffhand(){
        return offhand;
    }
}
