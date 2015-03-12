package com.fknussel.challengeo;

import retrofit.RestAdapter;

public class ApiClient {

    // API URL with NO TRAILING SLASH!
    private static final String API_URL = "http://restcountries.eu/rest/v1";

    public static ApiInterface getApiInterface() {
        // Create a very simple REST adapter which points to the API endpoint
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        // Create an instance of our API interface
        ApiInterface api = restAdapter.create(ApiInterface.class);
        return api;
    }
}
