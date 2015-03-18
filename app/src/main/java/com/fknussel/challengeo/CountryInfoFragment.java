package com.fknussel.challengeo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CountryInfoFragment extends Fragment {

    private static final String FLAG_BASE_URL = "http://www.geonames.org/flags/x/";
    private static final String FLAG_DEFAULT_EXTENSION = ".gif";
    
    private static String TAG = CountryInfoFragment.class.getSimpleName();
    
    public CountryInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View rootView = inflater.inflate(R.layout.fragment_country_info, container, false);

        Intent intent = getActivity().getIntent();
        final String code = intent.getStringExtra("code");

        final TextView codeView = (TextView) rootView.findViewById(R.id.country_code);
        final TextView nameView = (TextView) rootView.findViewById(R.id.country_name);
        final TextView populationView = (TextView) rootView.findViewById(R.id.country_population);
        final TextView regionView = (TextView) rootView.findViewById(R.id.country_region);
        final TextView subregionView = (TextView) rootView.findViewById(R.id.country_subregion);
        final ImageView flagView = (ImageView) rootView.findViewById(R.id.country_flag);
        final Button mapView = (Button) rootView.findViewById(R.id.country_map);

        ApiClient.getApiInterface().getCountry(code, new Callback<Country>() {
            @Override
            public void success(Country country, Response response) {
                String name = country.getName();
                String region = country.getRegion();
                String subregion = country.getSubregion();
                
                final double latitude = country.getLat();
                final double longitude = country.getLng();
                
                double population = country.getPopulation();
                String formattedPopulation = NumberFormat.getNumberInstance(Locale.US).format(population);

                codeView.setText(code);
                nameView.setText(name);
                // populationView.setText(String.valueOf((int) population));
                populationView.setText(formattedPopulation);
                regionView.setText(region);
                subregionView.setText(subregion);
                
                mapView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        getActivity().startActivity(intent);
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.getMessage());
            }
        });

        String flagFilename = FLAG_BASE_URL + code.toLowerCase() + FLAG_DEFAULT_EXTENSION;
        
        Picasso.with(getActivity())
                .load(flagFilename)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.broken_link)
                .into(flagView);
        
        return rootView;
    }
}