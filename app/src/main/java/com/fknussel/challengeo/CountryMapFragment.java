package com.fknussel.challengeo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CountryMapFragment extends Fragment implements Updatable {

    private TextView mapView;

    public CountryMapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_map, container, false);

        this.mapView = (TextView) view.findViewById(R.id.country_map);

        updateDisplay();

        return view;
    }

    public void updateDisplay() {

        CountryActivity activity = (CountryActivity) getActivity();
        Country country = activity.getCountry();

        this.mapView.setText(country.getName());
    }
}
