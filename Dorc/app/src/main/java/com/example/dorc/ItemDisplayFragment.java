package com.example.dorc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import static android.content.ContentValues.TAG;

public class ItemDisplayFragment extends Fragment {
    boolean inventory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.item_display, container, false);

        //ConstraintLayout parentConstraintLayout = rootView.findViewById(R.id.itemDisplayParent);

        SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);


        // WHERE ARE WE BEING CALLED FROM
        // TODO CHECK FOR NULL
        inventory = getArguments().getString("caller").equals("inventory");

        // FIND VIEWS
        ImageView iv = rootView.findViewById(R.id.itemToDisplay);
        EditText itemName = rootView.findViewById(R.id.itemName);
        TextView itemRarity = rootView.findViewById(R.id.itemRarity);
        TextView itemDamage = rootView.findViewById(R.id.itemDamage);

        // FIND BUTTON VIEWS
        Button equipButton = rootView.findViewById(R.id.equipBtn);
        Button sellButton = rootView.findViewById(R.id.sellBtn);
        Button exitButton = rootView.findViewById(R.id.exitItemDisplay);

        exitButton.setOnClickListener( view ->
                sharedViewModel.select(this)
        );

        sharedViewModel.getGearToDisplay().observe(this, gear -> {
           iv.setImageResource(gear.getIconId());
           itemName.setText(gear.getName());
           String rarity = gear.getRarity();
           SpannableString coloredRarity = SpannableString.valueOf(rarity);

            switch(rarity){
               case "LEGENDARY":
                   coloredRarity.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity().getApplicationContext(), android.R.color.holo_orange_dark)), 0,rarity.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                   break;
               case "EPIC":
                   coloredRarity.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity().getApplicationContext(), android.R.color.holo_purple)), 0,rarity.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                   break;
               case "RARE":
                   coloredRarity.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity().getApplicationContext(), android.R.color.holo_blue_light)), 0,rarity.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                   break;
               case "COMMON":
                   coloredRarity.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity().getApplicationContext(), android.R.color.darker_gray)), 0,rarity.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                   break;
           }

           String rarityConc = getString(R.string.item_rarity_placeholder) + ": ";
           CharSequence rarityDisplay = TextUtils.concat(SpannableString.valueOf(rarityConc), coloredRarity);
           String damageConc = getString(R.string.item_damage_placeholder) + ": " + gear.getStats().get("damage");
           itemRarity.setText(rarityDisplay);
           itemDamage.setText(damageConc);

           if(inventory) {
               Inventory playerInventory = MainActivity.testPlayer.getPlayerInventory();
               ItemSet playerSet = MainActivity.testPlayer.getItemSet();

               equipButton.setOnClickListener((View view) -> {
                   Gear oldGear = playerSet.setGear(gear);
                   if(oldGear != null){
                       Toast.makeText(getActivity().getApplicationContext(), R.string.swapped_i, Toast.LENGTH_SHORT).show();
                       playerInventory.getInvArray().set(playerInventory.getInvArray().indexOf(gear), oldGear);
                   }else{
                       Toast.makeText(getActivity().getApplicationContext(), R.string.equipped_i, Toast.LENGTH_SHORT).show();
                       playerInventory.removeItem(gear);
                   }
               });

           }else{
               equipButton.setText(R.string.upgrade_btn);
               //TODO upgrade functionality
               equipButton.setOnClickListener((View view) -> {
                   MainActivity.fadeAnimation(view);

                   ConstraintLayout fragContainer = rootView.findViewById(R.id.upgrade_fragment_container);

                   FragmentManager fragMan = getChildFragmentManager();
                   FragmentTransaction fragTransaction = fragMan.beginTransaction();

                   Fragment myFrag = new UpgradeFragment();
                   fragTransaction.add(fragContainer.getId(), myFrag, "upgrade_fragment");
                   fragTransaction.commit();
               });
           }
        });


        return rootView;
    }
}
