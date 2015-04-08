package com.fknussel.challengeo.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fknussel.challengeo.utils.AppHelper;
import com.fknussel.challengeo.R;
import com.fknussel.challengeo.activities.MainActivity;
import com.fknussel.challengeo.models.Country;
import com.fknussel.challengeo.networking.ApiClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashFragment extends Fragment {
    
    private static final String TAG = SplashFragment.class.getSimpleName();
    
    private OnDataLoadedListener callback;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().hide();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Hide action bar
        ((MainActivity)getActivity()).getSupportActionBar().hide();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_splash, container, false);

        ApiClient.getApiInterface().getAllCountries(new Callback<List<Country>>() {
            @Override
            public void success(List<Country> countries, Response response) {

                for (Country country : countries) {
                    AppHelper.listCountries.add(country);
                    AppHelper.mapCodes.put(country.getName(), country.getAlpha2Code());
                    AppHelper.listNames.add(country.getName());
                    Log.d(TAG, "Loaded Country: " + country.getName());
                }

                Log.d(TAG, "Done loading countries :) ----------------");

                AppHelper.loadLanguages();

                // Replace whatever is in the container view with this fragment
                callback.onDataLoaded();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.getMessage());
            }
        });
        
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        try {
            callback = (OnDataLoadedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnDataLoadedListener");
        }
    }

    public interface OnDataLoadedListener {

        public void onDataLoaded();
    }
}
