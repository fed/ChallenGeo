package com.fknussel.challengeo.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fknussel.challengeo.interfaces.Updatable;
import com.fknussel.challengeo.activities.CountryActivity;
import com.fknussel.challengeo.models.Country;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CountryMapFragment extends SupportMapFragment implements Updatable {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        try { // Make sure we can interact with the Google Map

            /*
             * MAP_TYPE_NORMAL: Basic map with roads
             * MAP_TYPE_SATELLITE: Satellite view with roads
             * MAP_TYPE_TERRAIN: Terrain view without roads
             */
            getMap().setMapType(GoogleMap.MAP_TYPE_NORMAL);

            // Show Zoom buttons
            getMap().getUiSettings().setZoomControlsEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    public void updateDisplay() {
        final Country country = ((CountryActivity)getActivity()).getCountry();
        final LatLng coords = new LatLng(country.getLat(), country.getLng());

        // Delete old markers
        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions().
                        position(coords).title(country.getName()));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, googleMap.getMinZoomLevel()));
            }
        });
    }
}
