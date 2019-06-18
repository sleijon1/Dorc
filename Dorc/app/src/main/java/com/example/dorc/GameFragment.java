package com.example.dorc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import static android.content.ContentValues.TAG;

public class GameFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.gameview_layout, container, false);
        final GameView gameView = rootView.findViewById(R.id.gameView);

        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        //TODO convert to lambda expression
        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                PlayerHit playerHit = new PlayerHit();
                if(playerHit.hit){
                    sharedViewModel.select(playerHit);
                }
                gameView.performClick();
                Log.i(TAG,"registered touch from fraggy");
                return false;
            }
        });

        return rootView;
    }

}
