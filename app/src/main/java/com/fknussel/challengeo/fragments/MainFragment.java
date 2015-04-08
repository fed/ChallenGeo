package com.fknussel.challengeo.fragments;

import android.app.Activity;
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

import com.fknussel.challengeo.utils.AppHelper;
import com.fknussel.challengeo.R;
import com.fknussel.challengeo.activities.MainActivity;

public class MainFragment extends Fragment {
    
    private String selection;
    
    private OnCountrySelectedListener countrySelectedListener;
    private OnRandomCountryRequestedListener randomCountryRequestedListener;
    private OnChallengeAcceptedListener challengeAcceptedListener;
    
    private static String TAG = MainFragment.class.getSimpleName();

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Show action bar (hidden on splash screen)
        ((MainActivity)getActivity()).getSupportActionBar().show();

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

                // Set focus to "Get country info" button to prevent ACTV
                // from showing again the selected option on screen rotation.
                // This method sets focus back to the first focusable view in the activity
                // that's why i needed to make the root view focusable on both layouts
                actv.clearFocus();

                // Dismiss soft keyboard
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                if (getActivity().getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                }
            }
        });
        
        // Set listeners to buttons on main screen
        getCountryInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selection != null) {

                    // Clear from previous searches
                    actv.setText("");
                    
                    countrySelectedListener.onCountrySelected(selection);
                } else {
                    Toast.makeText(getActivity(), R.string.no_country_selected, Toast.LENGTH_SHORT).show();
                }
            }
        });

        getRandomCountryInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomCountryRequestedListener.onRandomCountryRequested();
            }
        });

        challengeAcceptedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challengeAcceptedListener.onChallengeAccepted();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        try {
            countrySelectedListener = (OnCountrySelectedListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCountrySelectedListener interface");
        }

        try {
            randomCountryRequestedListener = (OnRandomCountryRequestedListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnRandomCountryRequestedListener interface");
        }

        try {
            challengeAcceptedListener = (OnChallengeAcceptedListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ChallengeAcceptedListener interface");
        }
    }

    public interface OnCountrySelectedListener {
        public void onCountrySelected(String name);
    }

    public interface OnRandomCountryRequestedListener {
        public void onRandomCountryRequested();
    }

    public interface OnChallengeAcceptedListener {
        public void onChallengeAccepted();
    }
}