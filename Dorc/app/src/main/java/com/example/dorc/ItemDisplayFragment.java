package com.example.dorc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class ItemDisplayFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.item_display, container, false);

        //ConstraintLayout parentConstraintLayout = rootView.findViewById(R.id.itemDisplayParent);

        SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        Button exitButton = rootView.findViewById(R.id.exitItemDisplay);

        exitButton.setOnClickListener( view ->
                sharedViewModel.select(this)
        );

        // FIND VIEWS
        ImageView iv = rootView.findViewById(R.id.itemToDisplay);
        EditText itemName = rootView.findViewById(R.id.itemName);
        TextView itemRarity = rootView.findViewById(R.id.itemRarity);
        TextView itemDamage = rootView.findViewById(R.id.itemDamage);

        sharedViewModel.getGearToDisplay().observe(this, gear -> {
           iv.setImageResource(gear.getIconId());
           itemName.setText(gear.getName());
           String rarity = gear.getRarity();
           SpannableString coloredRarity = SpannableString.valueOf(rarity);

            switch(rarity){
               case "LEGENDARY":
                   coloredRarity.setSpan(new ForegroundColorSpan(getResources().getColor(android.R.color.holo_orange_dark)), 0,rarity.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                   break;
               case "EPIC":
                   coloredRarity.setSpan(new ForegroundColorSpan(getResources().getColor(android.R.color.holo_purple)), 0,rarity.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                   break;
               case "RARE":
                   coloredRarity.setSpan(new ForegroundColorSpan(getResources().getColor(android.R.color.holo_blue_light)), 0,rarity.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                   break;
               case "COMMON":
                   coloredRarity.setSpan(new ForegroundColorSpan(getResources().getColor(android.R.color.darker_gray)), 0,rarity.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                   break;
           }

           String rarityConc = getString(R.string.item_rarity_placeholder) + ": ";
           CharSequence rarityDisplay = TextUtils.concat(SpannableString.valueOf(rarityConc), coloredRarity);
           String damageConc = getString(R.string.item_damage_placeholder) + ": " + gear.getDamage();
           itemRarity.setText(rarityDisplay);
           itemDamage.setText(damageConc);


        });

        return rootView;
    }
}
