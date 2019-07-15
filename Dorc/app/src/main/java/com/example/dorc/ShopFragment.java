package com.example.dorc;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShopFragment extends Fragment {
    View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.shop_layout, container, false);

        TabLayout tabLayout = rootView.findViewById(R.id.shopTabLayout);

        TabLayout.Tab bootsTab = tabLayout.getTabAt(0);
        TabLayout.Tab chestTab = tabLayout.getTabAt(1);
        TabLayout.Tab helmetTab = tabLayout.getTabAt(2);

        return rootView;
    }
}
