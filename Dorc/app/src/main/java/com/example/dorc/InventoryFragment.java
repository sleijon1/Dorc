package com.example.dorc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

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

        TableLayout childConstraintLayout = parentConstraintLayout.findViewById(R.id.childViewInvt);

        drawInventory(childConstraintLayout);

        return rootView;
    }

    //Add inventory argument that cycles through the "game inventory"
    public void drawInventory(TableLayout tableLayout){
        TableRow currentRow = null;
        Inventory playerInventory = MainActivity.testPlayer.getPlayerInventory();

        for(int i = 0; i < playerInventory.currentlyHeldItems ; i++) {
            ImageView iv = new ImageView(getActivity().getApplicationContext());
            iv.setImageDrawable(getResources().getDrawable(playerInventory.getInvArray()[i].getIconId()));
            iv.setBackgroundResource(R.drawable.invimageborder);
            iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            tableLayout.setShrinkAllColumns(true);

            if(i % 6 == 0) {
                // Create a new table row.
                TableRow tableRow = new TableRow(getActivity().getApplicationContext());
                // Set new table row layout parameters.
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(layoutParams);
                tableLayout.addView(tableRow);

                currentRow = tableRow;
            }
            currentRow.addView(iv, i%6);
        }



    }
}
