package com.example.dorc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.dorc.R;

public class UpgradeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.upgrade_item_layout, container, false);

    Button dmgUpgradeBtn = rootView.findViewById(R.id.dmgUpgradeBtn);

    return rootView;
    }
}