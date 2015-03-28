package com.fknussel.challengeo;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryInfoAdapter extends ArrayAdapter<CountryInfoItem> {

    public static final String TAG = CountryInfoAdapter.class.getSimpleName();

    private Context context;
    private ArrayList<CountryInfoItem> countryInformation;
    private Country country;

    public CountryInfoAdapter(Context context) {
        super(context, 0);
        this.context = context;
        this.countryInformation = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        CountryInfoItem item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_country_info, parent, false);
        }

        // Lookup view for data population
        TextView labelView = (TextView) convertView.findViewById(R.id.list_item_country_info_label);
        TextView valueView = (TextView) convertView.findViewById(R.id.list_item_country_info_value);

        // Populate the data into the template view using the data object
        labelView.setText(item.getLabel());
        valueView.setText(item.getValue());

        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getCount() {
        return countryInformation.size();
    }

    @Override
    public CountryInfoItem getItem(int position) {
        return countryInformation.get(position);
    }

    public void setCountry(Country country) {
        this.country = country;
        updateDataSet();
    }

    public void updateDataSet() {

        int i;

        this.countryInformation.clear();

        // -- Region and Subregion
        String region = this.country.getRegion();
        String subregion = this.country.getSubregion();
        if (region != null && subregion != null && !TextUtils.isEmpty(region) && !TextUtils.isEmpty(subregion)) {
            this.countryInformation.add(new CountryInfoItem("Region", this.country.getRegion() + " > " + this.country.getSubregion()));
        }

        // -- Capital City
        String capital = this.country.getCapital();
        if (capital != null && !TextUtils.isEmpty(capital)) {
            this.countryInformation.add(new CountryInfoItem("Capital City", this.country.getCapital()));
        }

        // -- Population
        double population = this.country.getPopulation();
        if (population != 0) {
            String formattedPopulation = NumberFormat.getNumberInstance(Locale.US).format(this.country.getPopulation());
            this.countryInformation.add(new CountryInfoItem("Population", formattedPopulation));
        }

        // -- Official Languages
        List<String> languages = this.country.getLanguages();
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

            this.countryInformation.add(new CountryInfoItem("Official Languages", textLanguages));
        }

        // -- Currencies
        List<String> currencies = this.country.getCurrencies();
        if (currencies != null && currencies.size() > 0 && !currencies.get(0).equals("")) {
            String textCurrencies = "";
            i = 1;
            for (String currency : currencies) {
                textCurrencies += currency;
                if (i++ != currencies.size()) {
                    textCurrencies += ", ";
                }
            }

            this.countryInformation.add(new CountryInfoItem("Currencies", textCurrencies));
        }

        // -- Timezones
        List<String> timezones = this.country.getTimezones();
        if (timezones != null && timezones.size() > 0 && !timezones.get(0).equals("")) {
            String textTimezones = "";
            i = 1;
            for (String timezone : timezones) {
                textTimezones += timezone;
                if (i++ != timezones.size()) {
                    textTimezones += ", ";
                }
            }

            this.countryInformation.add(new CountryInfoItem("Timezones", textTimezones));
        }

        // -- Calling Codes
        List<String> callingCodes = this.country.getCallingCodes();
        if (callingCodes != null && callingCodes.size() > 0 && !callingCodes.get(0).equals("")) {
            String textCallingCodes = "";
            i = 1;
            for (String callingCode : callingCodes) {
                textCallingCodes += "+" + callingCode;
                if (i++ != callingCodes.size()) {
                    textCallingCodes += ", ";
                }
            }

            this.countryInformation.add(new CountryInfoItem("Calling Codes", textCallingCodes));
        }

        // -- Top Level Domains
        List<String> tlds = this.country.getTopLevelDomain();
        if (tlds != null && tlds.size() > 0 && !tlds.get(0).equals("")) {
            String textTlds = "";
            i = 1;
            for (String tld : tlds) {
                textTlds += tld;
                if (i++ != tlds.size()) {
                    textTlds += ", ";
                }
            }

            this.countryInformation.add(new CountryInfoItem("Top Level Domains", textTlds));
        }
    }
}