package com.fknussel.challengeo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CountryFlagFragment extends Fragment implements Updatable {

    private ImageView flagView;
    private TextView nameView;

    public CountryFlagFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_flag, container, false);

        this.flagView = (ImageView) view.findViewById(R.id.country_flag);
        this.nameView = (TextView) view.findViewById(R.id.country_name);

        updateDisplay();

        return view;
    }

    public void updateFlag(String code) {
        String filename = AppHelper.FLAG_BASE_URL + code.toLowerCase() + AppHelper.FLAG_DEFAULT_EXTENSION;

        Picasso.with(getActivity())
                .load(filename)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.broken_link)
                .into(this.flagView);
    }

    private void updateName(String name) {
        this.nameView.setText(name);
    }

    public void updateDisplay() {
        Country country = ((CountryActivity)getActivity()).getCountry();
        updateFlag(country.getAlpha2Code());
        updateName(country.getName());
    }
}
