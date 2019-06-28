package com.example.dorc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

public class InventoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.inventory_layout, container, false);

        View parentConstraint = rootView.findViewById(R.id.parentView);

        SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        parentConstraint.setOnClickListener((View view)-> {
            sharedViewModel.select(this);
        });

        return rootView;
    }
}
