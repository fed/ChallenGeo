package com.fknussel.challengeo;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashFragment extends Fragment {
    
    private static final String TAG = SplashFragment.class.getSimpleName();

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_splash, container, false);

        final HashMap<String, String> map = new HashMap<>();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new MainFragment());
        transaction.addToBackStack(null);
        transaction.commit();

//        ApiClient.getApiInterface().getAllCountries(new Callback<List<Country>>() {
//            @Override
//            public void success(List<Country> countries, Response response) {
//
//                // TO DO: cache countries into the database
//                for (Country country : countries) {
//                    map.put(country.getAlpha2Code(), country.getName());
//                }
//
//                // Replace whatever is in the container view with this fragment,
//                // and add the transaction to the back stack
//                Fragment newFragment = new MainFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.container, newFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.d(TAG, error.getMessage());
//            }
//        });
        
        return rootView;
    }
}
