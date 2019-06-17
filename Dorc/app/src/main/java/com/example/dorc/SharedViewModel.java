package com.example.dorc;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.ClipData;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<PlayerHit> selected = new MutableLiveData<PlayerHit>();

    public void select(PlayerHit playerHit){
        selected.setValue(playerHit);
    }

    public LiveData<PlayerHit> getSelected(){
        return selected;
    }

}
