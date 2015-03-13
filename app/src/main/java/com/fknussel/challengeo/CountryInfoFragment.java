package com.fknussel.challengeo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CountryInfoFragment extends Fragment {

    private static String TAG = CountryInfoFragment.class.getSimpleName();
    
    public CountryInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View rootView = inflater.inflate(R.layout.fragment_country_info, container, false);

        Intent intent = getActivity().getIntent();
        final String code = intent.getStringExtra("code");

        final TextView countryCode = (TextView) rootView.findViewById(R.id.country_code);
        final TextView countryName = (TextView) rootView.findViewById(R.id.country_name);
        final TextView countryPopulation = (TextView) rootView.findViewById(R.id.country_population);
        final TextView countryRegion = (TextView) rootView.findViewById(R.id.country_region);
        final TextView countrySubregion = (TextView) rootView.findViewById(R.id.country_subregion);

        ApiClient.getApiInterface().getCountry(code, new Callback<Country>() {
            @Override
            public void success(Country country, Response response) {
                String name = country.getName();
                String region = country.getRegion();
                String subregion = country.getSubregion();
                double population = country.getPopulation();

                countryCode.setText(code);
                countryName.setText(name);
                countryPopulation.setText(String.valueOf((int) population));
                countryRegion.setText(region);
                countrySubregion.setText(subregion);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.getMessage());
            }
        });
        
        return rootView;
    }
}