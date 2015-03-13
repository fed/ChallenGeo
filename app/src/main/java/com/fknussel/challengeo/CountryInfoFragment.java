package com.fknussel.challengeo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CountryInfoFragment extends Fragment {

    public CountryInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_country_info, container, false);

        Intent intent = getActivity().getIntent();
        String code = intent.getStringExtra("code");
        
        TextView countryCode = (TextView) rootView.findViewById(R.id.country_code);
        countryCode.setText(code);
        
        return rootView;
    }
}