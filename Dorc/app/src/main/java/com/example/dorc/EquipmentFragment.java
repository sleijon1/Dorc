package com.example.dorc;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

public class EquipmentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.equipment_layout, container, false);

        ConstraintLayout parentConstraintLayout = rootView.findViewById(R.id.parentViewEq);

        SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        parentConstraintLayout.setOnClickListener((View view)-> {
            sharedViewModel.select(this);
        });

        ConstraintLayout childConstraintLayout = parentConstraintLayout.findViewById(R.id.childViewEq);


        return rootView;
    }
}
