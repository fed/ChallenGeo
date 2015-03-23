package com.fknussel.challengeo;

import java.util.ArrayList;
import java.util.HashMap;

public class AppHelper {
    
    private static final String TAG = AppHelper.class.getSimpleName();
    
    public static final ArrayList<Country> listCountries = new ArrayList<>();

    // Key: Country name, Value: Alpha2Code
    public static final HashMap<String, String> mapCodes = new HashMap<>();

    // This list just holds the names for all of the countries (used in AutoCompleteTextView)
    // Used as a workaround till building a HashMapAdapter!!!
    public static final ArrayList<String> listNames = new ArrayList<>();

}
