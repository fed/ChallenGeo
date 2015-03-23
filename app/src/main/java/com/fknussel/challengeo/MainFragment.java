package com.fknussel.challengeo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainFragment extends Fragment {
    
    private String selection;
    
    private static String TAG = MainFragment.class.getSimpleName();

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // List of countries with AutoComplete
        final AutoCompleteTextView actv = (AutoCompleteTextView) rootView.findViewById(R.id.selectCountry);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, AppHelper.listNames);
        
        // Buttons
        final Button getCountryInfoButton = (Button) rootView.findViewById(R.id.getCountryInfo);
        final Button getRandomCountryInfoButton = (Button) rootView.findViewById(R.id.getRandomCountryInfo);
        final Button challengeAcceptedButton = (Button) rootView.findViewById(R.id.challengeAccepted);
        
        // AutoCompleteTextView will start working from first character
        actv.setThreshold(1);

        // Setting the adapter data into the AutoCompleteTextView
        actv.setAdapter(adapter);

        // When a country gets selected, hide the soft keyboard
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selection = (String) parent.getItemAtPosition(position);
                
                Log.d(TAG, "Selected Country: " + selection + " (" + AppHelper.mapCodes.get(selection) + ")");

                // Dismiss soft keyboard
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        });
        
        // Set listeners to buttons on main screen
        getCountryInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selection != null) {

                    // Clear from previous searches
                    actv.setText("");
                    
                    Intent intent = new Intent(getActivity(), CountryInfoActivity.class);
                    intent.putExtra("name", selection);
                    intent.putExtra("code", AppHelper.mapCodes.get(selection));
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), R.string.no_country_selected, Toast.LENGTH_SHORT).show();
                }
            }
        });

        getRandomCountryInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CountryInfoActivity.class);
                
                int size = AppHelper.listNames.size();
                int min = 0;
                int max = size - 1;
                Random rand = new Random();
                int random = rand.nextInt((max - min) + 1) + min;
                
                String randomName = AppHelper.listNames.get(random);
                String randomCode = AppHelper.mapCodes.get(randomName);
                
                Log.d(TAG, "RANDOM: " + randomName + " (" + randomCode + ")");
                
                intent.putExtra("name", randomName);
                intent.putExtra("code", randomCode);

                // Prevents the new Activity from being added to the history stack
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                
                startActivity(intent);
            }
        });

        challengeAcceptedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChallengeActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
    
    
}
