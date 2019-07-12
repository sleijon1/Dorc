package com.example.dorc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import static android.content.ContentValues.TAG;

public class GameFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    private View rootView;
    PlayerHit playerHit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

        rootView = inflater.inflate(R.layout.gameview_layout, container, false);
        final GameView gameView = rootView.findViewById(R.id.gameView);

        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        //OBSERVERS
        sharedViewModel.getSelectedOrc().observe(this, (String selectedOrc)-> {
           gameView.updateOrc(selectedOrc);
            Log.i(TAG, "selectedOrc: " + selectedOrc);
        });

        sharedViewModel.getLifeCycleBool().observe(this, (Boolean starting)-> {
            if(starting){
                gameView.starting = true;
            }else{
                gameView.starting = false;
            }
            Log.i(TAG, "lifecyclebool: " + starting);
        });
        //TODO should be onclicklistener since motionevent isnt used
        gameView.setOnTouchListener( (View tGameView, MotionEvent event) -> {
                tGameView.startAnimation(animShake);

                playerHit = new PlayerHit();
                GameView currView = (GameView)tGameView;

                Orc currentOrc = currView.getCurrentOrc();
                int damage = currentOrc.hit();
                if(damage == 0){
                    playerHit.setMiss(true);
                    playerHit.setMissMessage(currentOrc.onMiss());
                }

                playerHit.setDamage(damage);


                if(currentOrc.getHealthBar() <= 0){
                    playerHit.setExperienceGained(currentOrc.getExperience());
                    playerHit.setFinishingBlow();

                    LootTable lootTable = new LootTable(currentOrc);
                    Gear loot = lootTable.calculateLoot();
                    playerHit.setLoot(loot);

                    currentOrc.setHealthBar(currentOrc.maxHealth);
                }

                sharedViewModel.select(playerHit);

                // For visually impaired etc.
                tGameView.performClick();
                Log.i(TAG,"registered touch from fraggy");
                return false;
        });

        return rootView;
    }

}
