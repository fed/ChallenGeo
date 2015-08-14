package com.fknussel.challengeo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fknussel.challengeo.interfaces.OnChallengeAcceptedListener;
import com.fknussel.challengeo.interfaces.OnCountrySelectedListener;
import com.fknussel.challengeo.interfaces.OnDataLoadedListener;
import com.fknussel.challengeo.interfaces.OnRandomCountryRequestedListener;
import com.fknussel.challengeo.utils.AppHelper;
import com.fknussel.challengeo.fragments.MainFragment;
import com.fknussel.challengeo.R;
import com.fknussel.challengeo.fragments.SplashFragment;


public class MainActivity extends ActionBarActivity 
        implements OnDataLoadedListener, OnCountrySelectedListener, OnRandomCountryRequestedListener, OnChallengeAcceptedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    
    private static boolean runOnce = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (runOnce) {
            runOnce = false;

            // First load the splash fragment
            // which will eventually take the user to the main fragment
            // once it has fetched the data from the API
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new SplashFragment())
                        .commit();
            }
        } else {
            // Data already fetched, take me to the main fragment
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new MainFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onDataLoaded() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new MainFragment());
        transaction.commit();
    }

    @Override
    public void onCountrySelected(String name) {
        Intent intent = new Intent(this, CountryActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("code", AppHelper.mapCodes.get(name));
        startActivity(intent);
    }

    @Override
    public void onRandomCountryRequested() {
        Intent intent = new Intent(this, CountryActivity.class);

        String randomName = AppHelper.getRandomCountryName();
        String randomCode = AppHelper.mapCodes.get(randomName);

        Log.d(TAG, "RANDOM: " + randomName + " (" + randomCode + ")");

        intent.putExtra("name", randomName);
        intent.putExtra("code", randomCode);

        // Prevents the new Activity from being added to the history stack
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);

        startActivity(intent);
    }

    @Override
    public void onChallengeAccepted() {
        Intent intent = new Intent(this, ChallengeActivity.class);
        startActivity(intent);
    }
}
