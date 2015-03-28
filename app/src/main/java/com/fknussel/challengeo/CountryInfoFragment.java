package com.fknussel.challengeo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryInfoFragment extends Fragment {
    
    private static String TAG = CountryInfoFragment.class.getSimpleName();

    CountryInfoAdapter customAdapter;
    private ImageView flagView;
    
    public CountryInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View rootView = inflater.inflate(R.layout.fragment_country_info, container, false);

        Intent intent = getActivity().getIntent();
        final String name = intent.getStringExtra("name");
        final String code = intent.getStringExtra("code");

        // Get Country
        int index = AppHelper.listNames.indexOf(name);
        Country country = AppHelper.listCountries.get(index);

        // Get views
        flagView = (ImageView) rootView.findViewById(R.id.country_flag);
        final ListView infoListView = (ListView) rootView.findViewById(R.id.country_info);

        // Set country name as title in action bar
        getActivity().setTitle(country.getName());

        // Create the adapter to convert the array into views
        customAdapter = new CountryInfoAdapter(getActivity());
        customAdapter.setCountry(country);

        // Attach the adapter to a ListView
        infoListView.setAdapter(customAdapter);

        updateFlag(country.getAlpha2Code());

                /*
                
                final double lat = country.getLat();
                final double lng = country.getLng();
        
                mapView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Map point based on latitude, longitude and zoom level
                        String uriBegin = "geo:" + lat + "," + lng ;
                        String query = lat + "," + lng + "(" + name + ")";
                        String encodedQuery = Uri.encode(query);
                        String uriString = uriBegin + "?q=" + encodedQuery + "&z=4";
                        Uri location = Uri.parse(uriString);
                        
                        //Uri location = Uri.parse("geo:" + lat + "," + lng + "?q=" + lat + "," + lng + "(" + name + ")&z=1");
                        //Uri location = Uri.parse("geo:" + lat + "," + lng + "?z=4&ll=" + lat + "," + lng);


//                        Uri location = Uri.parse("geo:" + lat + "," + lng)
//                                .buildUpon()
//                                .appendQueryParameter("q", lat + "," + lng + "(" + name + ")")
//                                .appendQueryParameter("z", "4")
//                                .build();
                        
                        
                        
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

                        // Verify There is an App to Receive the Intent
                        PackageManager packageManager = getActivity().getPackageManager();
                        List activities = packageManager.queryIntentActivities(mapIntent, PackageManager.MATCH_DEFAULT_ONLY);
                        boolean isIntentSafe = activities.size() > 0;
                        
                        if (isIntentSafe) {
                            startActivity(mapIntent);
                        } else {
                            Toast.makeText(getActivity(), "There isn't any maps application installed", Toast.LENGTH_SHORT).show();
                        }
                        
                        
                    }
                });
*/



        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_country_info, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_random) {

            // Get Country
            String name = AppHelper.getRandomCountryName();
            int index = AppHelper.listNames.indexOf(name);
            Country country = AppHelper.listCountries.get(index);

            getActivity().setTitle(country.getName());

            customAdapter.setCountry(country);
            customAdapter.notifyDataSetChanged();

            updateFlag(country.getAlpha2Code());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateFlag(String code) {
        String flagFilename = AppHelper.FLAG_BASE_URL + code.toLowerCase() + AppHelper.FLAG_DEFAULT_EXTENSION;

        Picasso.with(getActivity())
                .load(flagFilename)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.broken_link)
                .into(flagView);
    }

}