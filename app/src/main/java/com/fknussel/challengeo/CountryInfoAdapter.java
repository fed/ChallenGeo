package com.fknussel.challengeo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryInfoAdapter extends ArrayAdapter<CountryInfoItem> {

    public static final String TAG = CountryInfoAdapter.class.getSimpleName();

    public CountryInfoAdapter(Context context, ArrayList<CountryInfoItem> countryInfo) {
        super(context, 0, countryInfo);
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
}