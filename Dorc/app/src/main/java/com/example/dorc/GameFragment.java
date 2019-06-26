package com.example.dorc;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class GameFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    private View rootView;
    PlayerHit playerHit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.gameview_layout, container, false);
        final GameView gameView = rootView.findViewById(R.id.gameView);

        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        sharedViewModel.getSelectedOrc().observe(this, (String selectedOrc)-> {
           switch(selectedOrc){
               case "orcWarrior": break;
               case "orcMage": break;
               case "orcRogue": break;
           }
            Log.i(TAG, "selectedOrc: " + selectedOrc);
        });

        //TODO convert to lambda expression
        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View gameView, MotionEvent event) {
                //TODO
                //Starting animation here so app feels responsive even though
                //what happens on click should be decided by observer probably
                gameView.startAnimation(animShake);

                playerHit = new PlayerHit();
                //Typecast to GameView to be able to use GameView methods
                GameView currView = (GameView)gameView;

                Orc currentOrc = currView.getCurrentOrc();
                // TODO 5 is temp, should be set dynamically
                playerHit.setDamage(currentOrc.hit(5.0));

                if(currentOrc.getHealthBar() == 0){
                   //TODO Remove orc
                    playerHit.setFinishingBlow();
                    //Resets hp if finishing blow since no new orc has been selected
                    currentOrc.setHealthBar(currentOrc.maxHealth);
                }

                sharedViewModel.select(playerHit);


                // if above means orc is dead gameView.spawnNew !

                // For visually impaired etc.
                gameView.performClick();
                Log.i(TAG,"registered touch from fraggy");
                return false;
            }
        });

        return rootView;
    }

}
