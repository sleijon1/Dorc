package com.example.dorc;

public class ItemSet {
    private BasicHelmet helmet;
    private BasicChestpiece chestpiece;
    private BasicWeapon weapon;
    private BasicOffhand offhand;

    private int dmgBonus;

    public ItemSet(){
        helmet = null;
        chestpiece = null;
        weapon = null;
        offhand = null;
    }

    private void calculateBonuses(){
        //If helmet and chestpiece are of same sort
        //Add some stat etc.
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

    public Gear getHelmet(){
        return helmet;
    }

    public Gear getChestpiece(){
        return chestpiece;
    }

    public Gear getWeapon(){
        return weapon;
    }

    public Gear getOffhand(){
        return offhand;
    }
}
