package com.example.dorc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;

public class InventoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.inventory_layout, container, false);

        ConstraintLayout parentConstraintLayout = rootView.findViewById(R.id.parentViewInvt);

        SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        parentConstraintLayout.setOnClickListener((View view)-> {
            sharedViewModel.select(this);
        });

        ConstraintLayout childConstraintLayout = parentConstraintLayout.findViewById(R.id.childViewInvt);

        drawInventory(childConstraintLayout);

        return rootView;
    }

    //Add inventory argument that cycles through the "game inventory"
    public void drawInventory(ConstraintLayout constraintLayout){
        boolean itemDrawn = false;
        ImageView previousImage = null;

        for(int i = 0; i < 2; i++){
            ImageView iv = new ImageView(getActivity().getApplicationContext());
            iv.setImageDrawable(getResources().getDrawable(R.drawable.bagicon));
            iv.setId(i);
            constraintLayout.addView(iv);

            if(itemDrawn) {
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(iv.getId(), ConstraintSet.LEFT, previousImage.getId(), ConstraintSet.LEFT, 250);
                constraintSet.applyTo(constraintLayout);
            }

            previousImage = iv;
            itemDrawn = true;

            Log.i(TAG, "WE ARE MOST CERTAINLY IN HERE");
        }
    }
}
