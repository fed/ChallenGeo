package com.fknussel.challengeo;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().hide();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
                FragmentTransaction transaction = getFragmentManager().beginTransaction()
                        .replace(R.id.container, new MainFragment());
                transaction.commit();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.getMessage());
            }
        });
        
        return rootView;
    }
}
