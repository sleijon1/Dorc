package com.example.dorc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;

public class ItemDisplayFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.item_display, container, false);

        ConstraintLayout parentConstraintLayout = rootView.findViewById(R.id.itemDisplayParent);

        SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        Button exitButton = rootView.findViewById(R.id.exitItemDisplay);

        exitButton.setOnClickListener( view ->
                sharedViewModel.select(this)
        );

        sharedViewModel.getGearToDisplay().observe(this, gear -> {
           ImageView iv = rootView.findViewById(R.id.itemToDisplay);
           iv.setImageResource(gear.getIconId());

        });

        ConstraintLayout childConstraintLayout = parentConstraintLayout.findViewById(R.id.itemDisplayChild);

        return rootView;
    }
}
