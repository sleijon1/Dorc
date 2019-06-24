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
    Context currCtx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Need to do more research on this if it can be null etc
        currCtx = getActivity().getApplicationContext();
        final Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.gameview_layout, container, false);
        final GameView gameView2 = rootView.findViewById(R.id.gameView);

        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        //TODO convert to lambda expression
        gameView2.setOnTouchListener(new View.OnTouchListener() {
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
                playerHit.setCurrentOrc(currentOrc);

                sharedViewModel.select(playerHit);

                // For visually impaired etc.
                gameView.performClick();
                Log.i(TAG,"registered touch from fraggy");
                return false;
            }
        });

        return rootView;
    }

}
