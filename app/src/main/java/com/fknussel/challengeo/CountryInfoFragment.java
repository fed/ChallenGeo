package com.fknussel.challengeo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class CountryInfoFragment extends Fragment implements Updatable {
    
    private static String TAG = CountryInfoFragment.class.getSimpleName();

    CountryInfoAdapter customAdapter;
    
    public CountryInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Country country = ((CountryActivity)getActivity()).getCountry();

        if (country == null) {
            Intent intent = getActivity().getIntent();
            final String name = intent.getStringExtra("name");
            final String code = intent.getStringExtra("code");

            // Get the selected / random country
            int index = AppHelper.listNames.indexOf(name);
            country = AppHelper.listCountries.get(index);

            // Register the current country within the hosting activity
            ((CountryActivity)getActivity()).setCountry(country);
        }

        // Create the adapter to convert the array into views
        this.customAdapter = new CountryInfoAdapter(getActivity());
        this.customAdapter.setCountry(country);

        // Set country name as title in action bar
        getActivity().setTitle(country.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View rootView = inflater.inflate(R.layout.fragment_country_info, container, false);
        
        // Get reference to inflated views
        final ListView infoListView = (ListView) rootView.findViewById(R.id.country_info);

        // Attach the adapter to a ListView
        infoListView.setAdapter(customAdapter);

        updateDisplay();

        return rootView;
    }

    public void updateDisplay() {
        Country country = ((CountryActivity)getActivity()).getCountry();
        this.customAdapter.setCountry(country);
        this.customAdapter.notifyDataSetChanged();
    }
}