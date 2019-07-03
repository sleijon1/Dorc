package com.example.dorc;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ClipData;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class InventoryFragment extends Fragment {
    private static final String TAG = "inventory fragment";
    SharedViewModel sharedViewModel;
    View rootView;
    TableLayout childTableLayout;
    FragmentManager fragMan;
    Inventory playerInventory;
    ConstraintLayout parentConstraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.inventory_layout, container, false);

        parentConstraintLayout = rootView.findViewById(R.id.parentViewInvt);


        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        fragMan = getChildFragmentManager();

        sharedViewModel.getItemDisplay().observe(this, (ItemDisplayFragment fragment) -> {
                if(fragment != null){
                    fragMan.beginTransaction().remove(fragment).commit();
                 }
                childTableLayout.setAlpha(1);

                for(int i = 0; i < playerInventory.currentlyHeldItems; i++){
                    ImageButton iv = childTableLayout.findViewWithTag(i);
                    iv.setClickable(true);
                }
        });

        parentConstraintLayout.setOnClickListener( view -> sharedViewModel.select(this, true));

        childTableLayout = parentConstraintLayout.findViewById(R.id.childViewInvt);



        drawInventory(childTableLayout);

        return rootView;
    }

    //Add inventory argument that cycles through the "game inventory"
    public void drawInventory(TableLayout tableLayout){
        TableRow currentRow = null;
        playerInventory = MainActivity.testPlayer.getPlayerInventory();
        int inventorySpace = playerInventory.getTotalSpace();

        for(int i = 0; i < inventorySpace ; i++) {
            ImageButton iv = new ImageButton(getActivity().getApplicationContext());
            iv.setBackgroundResource(R.drawable.invimageborder2);
            iv.setScaleType(ImageButton.ScaleType.CENTER_INSIDE);
            iv.setImageResource(R.drawable.fillerimginv);
            tableLayout.setShrinkAllColumns(true);

            iv.setTag(i);

            if(i % 4 == 0) {
                // Create a new table row.
                TableRow tableRow = new TableRow(getActivity().getApplicationContext());
                // Set new table row layout parameters.
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(layoutParams);
                tableLayout.addView(tableRow);

                currentRow = tableRow;
            }
            currentRow.addView(iv, i%4);

        }

        for(int i = 0; i < playerInventory.currentlyHeldItems; i++){
            Gear item = playerInventory.getInvArray(i);
            ImageButton iv = tableLayout.findViewWithTag(i);
            iv.setImageResource(item.getIconId());
            iv.setScaleType(ImageButton.ScaleType.CENTER_INSIDE);
            iv.setOnClickListener(this::setButton);
        }



    }

    public void setButton(View view){
        MainActivity.fadeAnimation(view);
        LinearLayout fragContainer = rootView.findViewById(R.id.itemDisplayContainer);
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        Fragment myFrag = new ItemDisplayFragment();
        fragTransaction.add(fragContainer.getId(), myFrag, "item display fragment");
        fragTransaction.commit();
        childTableLayout.setAlpha((float)0.5);

        for(int i = 0; i < playerInventory.currentlyHeldItems; i++){
            ImageButton iv = childTableLayout.findViewWithTag(i);
            iv.setClickable(false);
        }
        sharedViewModel.select(playerInventory.getInvArray((int)view.getTag()));
    }


}
