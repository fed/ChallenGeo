package com.fknussel.challengeo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fknussel.challengeo.utils.AppHelper;
import com.fknussel.challengeo.fragments.MainFragment;
import com.fknussel.challengeo.R;
import com.fknussel.challengeo.fragments.SplashFragment;


public class MainActivity extends ActionBarActivity 
        implements SplashFragment.OnDataLoadedListener, MainFragment.OnCountrySelectedListener, MainFragment.OnRandomCountryRequestedListener, MainFragment.OnChallengeAcceptedListener {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
