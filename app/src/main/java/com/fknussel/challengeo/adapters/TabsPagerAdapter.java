package com.fknussel.challengeo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fknussel.challengeo.fragments.CountryFlagFragment;
import com.fknussel.challengeo.fragments.CountryInfoFragment;
import com.fknussel.challengeo.fragments.CountryMapFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // General information
                return new CountryInfoFragment();
            case 1:
                // Flag
                return new CountryFlagFragment();
            case 2:
                // Location on map
                return new CountryMapFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
}