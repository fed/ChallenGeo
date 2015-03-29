package com.fknussel.challengeo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CountryInfoFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;

    private String tabTitles[] = new String[] { "Info", "Flag", "Map" };

    public CountryInfoFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new CountryInfoFragment();
                break;
            case 1:
                fragment = new CountryFlagFragment();
                break;
            case 2:
                fragment = new CountryMapFragment();
                break;
            default:
                fragment = new CountryInfoFragment();
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}