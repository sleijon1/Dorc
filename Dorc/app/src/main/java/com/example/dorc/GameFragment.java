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

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class GameFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    private View rootView;
    PlayerHit playerHit = new PlayerHit();
    Context currCtx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Need to do more research on this if it can be null etc
        currCtx = getActivity().getApplicationContext();
        final Animation animShake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.gameview_layout, container, false);
        final GameView gameView = rootView.findViewById(R.id.gameView);

        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        //TODO convert to lambda expression
        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                gameView.startAnimation(animShake);
                // Should only register one click a second
                if(playerHit.hit){
                    sharedViewModel.select(playerHit);
                }
                // For visually impaired etc.
                gameView.performClick();
                Log.i(TAG,"registered touch from fraggy");
                return false;
            }
        });

        return rootView;
    }

}
