package com.example.dorc;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.app.Fragment;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<PlayerHit> selected = new MutableLiveData<PlayerHit>();
    private final MutableLiveData<String> selectedOrc = new MutableLiveData<String>();
    private final MutableLiveData<Boolean> paused = new MutableLiveData<>();
    private final MutableLiveData<Fragment> toCancel = new MutableLiveData<>();

    public void select(PlayerHit playerHit){
        selected.setValue(playerHit);
    }

    public void select(String string){
        selectedOrc.setValue(string);
    }

    public void select(Boolean starting){ paused.setValue(starting); }

    public void select(Fragment fragment){ toCancel.setValue(fragment); }

    public LiveData<PlayerHit> getSelected(){
        return selected;
    }

    public LiveData<String> getSelectedOrc(){
        return selectedOrc;
    }

    public LiveData<Boolean> getLifeCycleBool(){ return paused; }

    public LiveData<Fragment> getFragmentToCancel(){ return toCancel; }
}
