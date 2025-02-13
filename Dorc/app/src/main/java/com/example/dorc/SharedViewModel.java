package com.example.dorc;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.app.Fragment;
import android.widget.ImageButton;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<PlayerHit> selected = new MutableLiveData<PlayerHit>();
    private final MutableLiveData<String> selectedOrc = new MutableLiveData<String>();
    private final MutableLiveData<Boolean> paused = new MutableLiveData<>();
    private final MutableLiveData<Fragment> toCancel = new MutableLiveData<>();
    private final MutableLiveData<Fragment> toCommit = new MutableLiveData<>();
    private final MutableLiveData<Gear> gearToDisplay = new MutableLiveData<>();
    private final MutableLiveData<Gear> gearToUpgrade = new MutableLiveData<>();
    private final MutableLiveData<ItemDisplayFragment> cancelFrag = new MutableLiveData<>();
    private final MutableLiveData<ItemSet> playerGear = new MutableLiveData<>();
    private final MutableLiveData<ImageButton>  imgBtn = new MutableLiveData<>();

    public void select(ItemSet itemSet){playerGear.setValue(itemSet);}

    public void select(ImageButton ib){imgBtn.setValue(ib);}

    public void select(PlayerHit playerHit){
        selected.setValue(playerHit);
    }

    public void select(String string){
        selectedOrc.setValue(string);
    }

    public void select(Boolean starting){ paused.setValue(starting); }

    public void select(Fragment fragment, boolean cancel){
        if(cancel){
            toCancel.setValue(fragment);
        }else{
            toCommit.setValue(fragment);
        }
    }

    public void select(ItemDisplayFragment fragment){ cancelFrag.setValue(fragment);}

    public void select(Gear toDisplay){ gearToDisplay.setValue(toDisplay); }

    public void select(Gear toUpgrade, ItemDisplayFragment fragment){ gearToUpgrade.setValue(toUpgrade);}

    public LiveData<ImageButton> getImgBtn(){return imgBtn;}

    public LiveData<ItemSet> getItemSet(){return playerGear;}

    public LiveData<PlayerHit> getSelected(){
        return selected;
    }

    public LiveData<Gear> getGearToUpgrade(){
        return gearToUpgrade;
    }

    public LiveData<String> getSelectedOrc(){
        return selectedOrc;
    }

    public LiveData<Boolean> getLifeCycleBool(){ return paused; }

    public LiveData<Fragment> getFragmentToCancel(){ return toCancel; }

    public LiveData<Fragment> getFragmentToCommit(){ return toCommit; }

    public LiveData<Gear> getGearToDisplay(){ return gearToDisplay; }

    public LiveData<ItemDisplayFragment> getItemDisplay(){return cancelFrag; }
}
