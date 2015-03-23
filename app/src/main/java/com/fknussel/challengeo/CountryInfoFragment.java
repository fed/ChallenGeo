package com.fknussel.challengeo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View rootView = inflater.inflate(R.layout.fragment_country_info, container, false);

        Intent intent = getActivity().getIntent();
        final String name = intent.getStringExtra("name");
        final String code = intent.getStringExtra("code");

        // Get Country
        int index = AppHelper.listNames.indexOf(name);
        Country country = AppHelper.listCountries.get(index);
        
        // Get views
        final ImageView flagView = (ImageView) rootView.findViewById(R.id.country_flag);
        final ListView infoListView = (ListView) rootView.findViewById(R.id.country_info);
        
        int i;

        getActivity().setTitle(name);

        // ListView data source
        ArrayList<CountryInfoItem> countryInformation = new ArrayList<>();
        
        // -- Native name
        String nativeName = country.getNativeName();
        if (nativeName != null && !TextUtils.isEmpty(nativeName)) {
            countryInformation.add(new CountryInfoItem("Native Name", country.getNativeName()));
        }
        
        // -- Region and Subregion
        String region = country.getRegion();
        String subregion = country.getSubregion();
        if (region != null && subregion != null && !TextUtils.isEmpty(region) && !TextUtils.isEmpty(subregion)) {
            countryInformation.add(new CountryInfoItem("Region", country.getRegion() + " > " + country.getSubregion()));
        }

        // -- Capital City
        String capital = country.getCapital();
        if (capital != null && !TextUtils.isEmpty(capital)) {
            countryInformation.add(new CountryInfoItem("Capital City", country.getCapital()));
        }
        
        // -- Population
        double population = country.getPopulation();
        if (population != 0) {
            String formattedPopulation = NumberFormat.getNumberInstance(Locale.US).format(country.getPopulation());
            countryInformation.add(new CountryInfoItem("Population", formattedPopulation));
        }
        
        // -- Official Languages
        List<String> languages = country.getLanguages();
        if (languages != null && languages.size() > 0 && !languages.get(0).equals("")) {
            String textLanguages = "";
            i = 1;
            for (String language : languages) {
                if (AppHelper.mapLanguages.get(language.toUpperCase()) != null) {
                    textLanguages += AppHelper.mapLanguages.get(language.toUpperCase());
                } else {
                    textLanguages += language;
                }
                
                if (i++ != languages.size()) {
                    textLanguages += ", ";
                }
            }
            
            countryInformation.add(new CountryInfoItem("Official Languages", textLanguages));
        }
        
        // -- Currencies
        List<String> currencies = country.getCurrencies();
        if (currencies != null && currencies.size() > 0 && !currencies.get(0).equals("")) {
            String textCurrencies = "";
            i = 1;
            for (String currency : currencies) {
                textCurrencies += currency;
                if (i++ != currencies.size()) {
                    textCurrencies += ", ";
                }
            }
            
            countryInformation.add(new CountryInfoItem("Currencies", textCurrencies));    
        }
        
        // -- Timezones
        List<String> timezones = country.getTimezones();
        if (timezones != null && timezones.size() > 0 && !timezones.get(0).equals("")) {
            String textTimezones = "";
            i = 1;
            for (String timezone : timezones) {
                textTimezones += timezone;
                if (i++ != timezones.size()) {
                    textTimezones += ", ";
                }
            }
            
            countryInformation.add(new CountryInfoItem("Timezones", textTimezones));
        }
       
        // -- Calling Codes
        List<String> callingCodes = country.getCallingCodes();
        if (callingCodes != null && callingCodes.size() > 0 && !callingCodes.get(0).equals("")) {
            String textCallingCodes = "";
            i = 1;
            for (String callingCode : callingCodes) {
                textCallingCodes += "+" + callingCode;
                if (i++ != callingCodes.size()) {
                    textCallingCodes += ", ";
                }
            }
            
            countryInformation.add(new CountryInfoItem("Calling Codes", textCallingCodes));
        }
        
        // -- Top Level Domains
        List<String> tlds = country.getTopLevelDomain();
        if (tlds != null && tlds.size() > 0 && !tlds.get(0).equals("")) {
            String textTlds = "";
            i = 1;
            for (String tld : tlds) {
                textTlds += tld;
                if (i++ != tlds.size()) {
                    textTlds += ", ";
                }
            }
            
            countryInformation.add(new CountryInfoItem("Top Level Domains", textTlds));
        }

        // Create the adapter to convert the array to views
        CountryInfoAdapter customAdapter = new CountryInfoAdapter(getActivity(), countryInformation);

        // Attach the adapter to a ListView
        infoListView.setAdapter(customAdapter);

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

        String flagFilename = FLAG_BASE_URL + code.toLowerCase() + FLAG_DEFAULT_EXTENSION;
        
        Picasso.with(getActivity())
                .load(flagFilename)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.broken_link)
                .into(flagView);

        return rootView;
    }
}