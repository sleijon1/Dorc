package com.example.dorc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import static android.content.ContentValues.TAG;

public class EquipmentFragment extends Fragment {
    ImageButton weaponBtn;
    ImageButton offhandBtn;
    ImageButton helmetBtn;
    ImageButton chestBtn;
    ItemSet itemSet;
    View rootView;
    FragmentManager fragMan;
    ConstraintLayout childConstraintLayout;
    SharedViewModel sharedViewModel;
    Gear currentlyConfiguring;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.equipment_layout, container, false);

        ConstraintLayout parentConstraintLayout = rootView.findViewById(R.id.parentViewEq);

        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        fragMan = getChildFragmentManager();

        parentConstraintLayout.setOnClickListener( view ->
            sharedViewModel.select(this, true)
        );

        sharedViewModel.getItemDisplay().observe(this, (ItemDisplayFragment fragment) -> {
            if(fragment != null){
                fragMan.beginTransaction().remove(fragment).commit();
            }
            childConstraintLayout.setAlpha(1);

            weaponBtn.setClickable(true);
            chestBtn.setClickable(true);
            offhandBtn.setClickable(true);
            helmetBtn.setClickable(true);
        });

        childConstraintLayout = parentConstraintLayout.findViewById(R.id.childViewEq);

        // FIND IMAGEBUTTON VIEWS
        weaponBtn = childConstraintLayout.findViewById(R.id.btnPlayerWeapon);
        offhandBtn = childConstraintLayout.findViewById(R.id.btnPlayerOffh);
        helmetBtn = childConstraintLayout.findViewById(R.id.btnPlayerHead);
        chestBtn = childConstraintLayout.findViewById(R.id.btnPlayerChest);

        itemSet = MainActivity.testPlayer.getItemSet();

        // COLLECT ITEMSET
        Gear helmet = itemSet.getHelmet();
        Gear chestpiece = itemSet.getChestpiece();
        Gear weapon = itemSet.getWeapon();
        Gear offhand = itemSet.getOffhand();

        configureButtons(helmet, helmetBtn);
        configureButtons(chestpiece, chestBtn);
        configureButtons(weapon, weaponBtn);
        configureButtons(offhand, offhandBtn);

        return rootView;
    }

    public void configureButtons(Gear gear, ImageButton imgBtn){
        if(gear != null){
            imgBtn.setImageResource(gear.getIconId());
            imgBtn.setScaleType(ImageButton.ScaleType.CENTER_INSIDE);
            setOnClick(imgBtn, gear);
        }
    }

    // Doesn't need to be separate function but more readable
    public void setOnClick(ImageButton btn, Gear gear){

        btn.setOnClickListener( view -> {
                MainActivity.fadeAnimation(view);
                LinearLayout fragContainer = rootView.findViewById(R.id.itemDisplayContainer);
                FragmentTransaction fragTransaction = fragMan.beginTransaction();

                Fragment itemDisplayFragment = new ItemDisplayFragment();

                Bundle bundle = new Bundle();
                bundle.putString("caller", "equipment");
                itemDisplayFragment.setArguments(bundle);

                fragTransaction.add(fragContainer.getId(), itemDisplayFragment, "item display fragment");
                fragTransaction.commit();
                childConstraintLayout.setAlpha((float) 0.9);

                weaponBtn.setClickable(false);
                offhandBtn.setClickable(false);
                chestBtn.setClickable(false);
                helmetBtn.setClickable(false);

                sharedViewModel.select(gear);
        });
    }
}
