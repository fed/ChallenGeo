package com.fknussel.challengeo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainFragment extends android.support.v4.app.Fragment {
    
    private String selection;
    
    private static String TAG = MainFragment.class.getSimpleName();

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Creating the instance of ArrayAdapter containing list of countries
        final HashMap<String, String> countries = loadAutoCompleteOptions();

        final ArrayList<String> names = new ArrayList<>();
        for(Map.Entry<String, String> country : countries.entrySet()) {
            names.add(country.getKey());
        }
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, names);
        
        // Getting the instance of AutoCompleteTextView
        final AutoCompleteTextView actv = (AutoCompleteTextView) rootView.findViewById(R.id.selectCountry);
        actv.setThreshold(1); // Will start working from first character
        actv.setAdapter(adapter); // Setting the adapter data into the AutoCompleteTextView
        
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selection = (String) parent.getItemAtPosition(position);
                
                // dismiss soft keyboard
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        });

        // Set listeners to buttons on main screen
        Button getCountryInfoButton = (Button) rootView.findViewById(R.id.getCountryInfo);
        getCountryInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CountryInfoActivity.class);
                intent.putExtra("code", countries.get(selection));
                startActivity(intent);
            }
        });

        Button getRandomCountryInfoButton = (Button) rootView.findViewById(R.id.getRandomCountryInfo);
        getRandomCountryInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CountryInfoActivity.class);
                
                int size = names.size();
                int min = 0;
                int max = size - 1;
                Random rand = new Random();
                int random = rand.nextInt((max - min) + 1) + min;

                String randomCode = countries.get(names.get(random));
                
                intent.putExtra("code", randomCode);
                
                startActivity(intent);
            }
        });

        Button challengeAcceptedButton = (Button) rootView.findViewById(R.id.challengeAccepted);
        challengeAcceptedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChallengeActivity.class);
                startActivity(intent);
            }
        });
        
        return rootView;
    }
    
    private static HashMap<String, String> loadAutoCompleteOptions() {

        HashMap<String, String> countries = new HashMap<>();

        countries.put("Afghanistan", "AF");
        countries.put("Albania", "AL");
        countries.put("Algeria", "DZ");
        countries.put("American Samoa", "AS");
        countries.put("Andorra", "AD");
        countries.put("Angola", "AO");
        countries.put("Anguilla", "AI");
        countries.put("Antarctica", "AQ");
        countries.put("Antigua and Barbuda", "AG");
        countries.put("Argentina", "AR");
        countries.put("Armenia", "AM");
        countries.put("Aruba", "AW");
        countries.put("Australia", "AU");
        countries.put("Austria", "AT");
        countries.put("Azerbaijan", "AZ");
        countries.put("Bahamas", "BS");
        countries.put("Bahrain", "BH");
        countries.put("Bangladesh", "BD");
        countries.put("Barbados", "BB");
        countries.put("Belarus", "BY");
        countries.put("Belgium", "BE");
        countries.put("Belize", "BZ");
        countries.put("Benin", "BJ");
        countries.put("Bermuda", "BM");
        countries.put("Bhutan", "BT");
        countries.put("Bolivia", "BO");
        countries.put("Bosnia and Herzegovina", "BA");
        countries.put("Botswana", "BW");
        countries.put("Bouvet Island", "BV");
        countries.put("Brazil", "BR");
        countries.put("British Antarctic Territory", "BQ");
        countries.put("British Indian Ocean Territory", "IO");
        countries.put("British Virgin Islands", "VG");
        countries.put("Brunei", "BN");
        countries.put("Bulgaria", "BG");
        countries.put("Burkina Faso", "BF");
        countries.put("Burundi", "BI");
        countries.put("Cambodia", "KH");
        countries.put("Cameroon", "CM");
        countries.put("Canada", "CA");
        countries.put("Canton and Enderbury Islands", "CT");
        countries.put("Cape Verde", "CV");
        countries.put("Cayman Islands", "KY");
        countries.put("Central African Republic", "CF");
        countries.put("Chad", "TD");
        countries.put("Chile", "CL");
        countries.put("China", "CN");
        countries.put("Christmas Island", "CX");
        countries.put("Cocos (Keeling) Islands", "CC");
        countries.put("Colombia", "CO");
        countries.put("Comoros", "KM");
        countries.put("Congo - Brazzaville", "CG");
        countries.put("Congo - Kinshasa", "CD");
        countries.put("Cook Islands", "CK");
        countries.put("Costa Rica", "CR");
        countries.put("Croatia", "HR");
        countries.put("Cuba", "CU");
        countries.put("Cyprus", "CY");
        countries.put("Czech Republic", "CZ");
        countries.put("Côte d’Ivoire", "CI");
        countries.put("Denmark", "DK");
        countries.put("Djibouti", "DJ");
        countries.put("Dominica", "DM");
        countries.put("Dominican Republic", "DO");
        countries.put("Dronning Maud Land", "NQ");
        countries.put("East Germany", "DD");
        countries.put("Ecuador", "EC");
        countries.put("Egypt", "EG");
        countries.put("El Salvador", "SV");
        countries.put("Equatorial Guinea", "GQ");
        countries.put("Eritrea", "ER");
        countries.put("Estonia", "EE");
        countries.put("Ethiopia", "ET");
        countries.put("Falkland Islands", "FK");
        countries.put("Faroe Islands", "FO");
        countries.put("Fiji", "FJ");
        countries.put("Finland", "FI");
        countries.put("France", "FR");
        countries.put("French Guiana", "GF");
        countries.put("French Polynesia", "PF");
        countries.put("French Southern Territories", "TF");
        countries.put("French Southern and Antarctic Territories", "FQ");
        countries.put("Gabon", "GA");
        countries.put("Gambia", "GM");
        countries.put("Georgia", "GE");
        countries.put("Germany", "DE");
        countries.put("Ghana", "GH");
        countries.put("Gibraltar", "GI");
        countries.put("Greece", "GR");
        countries.put("Greenland", "GL");
        countries.put("Grenada", "GD");
        countries.put("Guadeloupe", "GP");
        countries.put("Guam", "GU");
        countries.put("Guatemala", "GT");
        countries.put("Guernsey", "GG");
        countries.put("Guinea", "GN");
        countries.put("Guinea-Bissau", "GW");
        countries.put("Guyana", "GY");
        countries.put("Haiti", "HT");
        countries.put("Heard Island and McDonald Islands", "HM");
        countries.put("Honduras", "HN");
        countries.put("Hong Kong SAR China", "HK");
        countries.put("Hungary", "HU");
        countries.put("Iceland", "IS");
        countries.put("India", "IN");
        countries.put("Indonesia", "ID");
        countries.put("Iran", "IR");
        countries.put("Iraq", "IQ");
        countries.put("Ireland", "IE");
        countries.put("Isle of Man", "IM");
        countries.put("Israel", "IL");
        countries.put("Italy", "IT");
        countries.put("Jamaica", "JM");
        countries.put("Japan", "JP");
        countries.put("Jersey", "JE");
        countries.put("Johnston Island", "JT");
        countries.put("Jordan", "JO");
        countries.put("Kazakhstan", "KZ");
        countries.put("Kenya", "KE");
        countries.put("Kiribati", "KI");
        countries.put("Kuwait", "KW");
        countries.put("Kyrgyzstan", "KG");
        countries.put("Laos", "LA");
        countries.put("Latvia", "LV");
        countries.put("Lebanon", "LB");
        countries.put("Lesotho", "LS");
        countries.put("Liberia", "LR");
        countries.put("Libya", "LY");
        countries.put("Liechtenstein", "LI");
        countries.put("Lithuania", "LT");
        countries.put("Luxembourg", "LU");
        countries.put("Macau SAR China", "MO");
        countries.put("Macedonia", "MK");
        countries.put("Madagascar", "MG");
        countries.put("Malawi", "MW");
        countries.put("Malaysia", "MY");
        countries.put("Maldives", "MV");
        countries.put("Mali", "ML");
        countries.put("Malta", "MT");
        countries.put("Marshall Islands", "MH");
        countries.put("Martinique", "MQ");
        countries.put("Mauritania", "MR");
        countries.put("Mauritius", "MU");
        countries.put("Mayotte", "YT");
        countries.put("Metropolitan France", "FX");
        countries.put("Mexico", "MX");
        countries.put("Micronesia", "FM");
        countries.put("Midway Islands", "MI");
        countries.put("Moldova", "MD");
        countries.put("Monaco", "MC");
        countries.put("Mongolia", "MN");
        countries.put("Montenegro", "ME");
        countries.put("Montserrat", "MS");
        countries.put("Morocco", "MA");
        countries.put("Mozambique", "MZ");
        countries.put("Myanmar (Burma)", "MM");
        countries.put("Namibia", "NA");
        countries.put("Nauru", "NR");
        countries.put("Nepal", "NP");
        countries.put("Netherlands", "NL");
        countries.put("Netherlands Antilles", "AN");
        countries.put("Neutral Zone", "NT");
        countries.put("New Caledonia", "NC");
        countries.put("New Zealand", "NZ");
        countries.put("Nicaragua", "NI");
        countries.put("Niger", "NE");
        countries.put("Nigeria", "NG");
        countries.put("Niue", "NU");
        countries.put("Norfolk Island", "NF");
        countries.put("North Korea", "KP");
        countries.put("North Vietnam", "VD");
        countries.put("Northern Mariana Islands", "MP");
        countries.put("Norway", "NO");
        countries.put("Oman", "OM");
        countries.put("Pacific Islands Trust Territory", "PC");
        countries.put("Pakistan", "PK");
        countries.put("Palau", "PW");
        countries.put("Palestinian Territories", "PS");
        countries.put("Panama", "PA");
        countries.put("Panama Canal Zone", "PZ");
        countries.put("Papua New Guinea", "PG");
        countries.put("Paraguay", "PY");
        countries.put("People's Democratic Republic of Yemen", "YD");
        countries.put("Peru", "PE");
        countries.put("Philippines", "PH");
        countries.put("Pitcairn Islands", "PN");
        countries.put("Poland", "PL");
        countries.put("Portugal", "PT");
        countries.put("Puerto Rico", "PR");
        countries.put("Qatar", "QA");
        countries.put("Romania", "RO");
        countries.put("Russia", "RU");
        countries.put("Rwanda", "RW");
        countries.put("Réunion", "RE");
        countries.put("Saint Barthélemy", "BL");
        countries.put("Saint Helena", "SH");
        countries.put("Saint Kitts and Nevis", "KN");
        countries.put("Saint Lucia", "LC");
        countries.put("Saint Martin", "MF");
        countries.put("Saint Pierre and Miquelon", "PM");
        countries.put("Saint Vincent and the Grenadines", "VC");
        countries.put("Samoa", "WS");
        countries.put("San Marino", "SM");
        countries.put("Saudi Arabia", "SA");
        countries.put("Senegal", "SN");
        countries.put("Serbia", "RS");
        countries.put("Serbia and Montenegro", "CS");
        countries.put("Seychelles", "SC");
        countries.put("Sierra Leone", "SL");
        countries.put("Singapore", "SG");
        countries.put("Slovakia", "SK");
        countries.put("Slovenia", "SI");
        countries.put("Solomon Islands", "SB");
        countries.put("Somalia", "SO");
        countries.put("South Africa", "ZA");
        countries.put("South Georgia and the South Sandwich Islands", "GS");
        countries.put("South Korea", "KR");
        countries.put("Spain", "ES");
        countries.put("Sri Lanka", "LK");
        countries.put("Sudan", "SD");
        countries.put("Suriname", "SR");
        countries.put("Svalbard and Jan Mayen", "SJ");
        countries.put("Swaziland", "SZ");
        countries.put("Sweden", "SE");
        countries.put("Switzerland", "CH");
        countries.put("Syria", "SY");
        countries.put("São Tomé and Príncipe", "ST");
        countries.put("Taiwan", "TW");
        countries.put("Tajikistan", "TJ");
        countries.put("Tanzania", "TZ");
        countries.put("Thailand", "TH");
        countries.put("TL", "Timor-Leste");
        countries.put("Togo", "TG");
        countries.put("Tokelau", "TK");
        countries.put("Tonga", "TO");
        countries.put("Trinidad and Tobago", "TT");
        countries.put("Tunisia", "TN");
        countries.put("Turkey", "TR");
        countries.put("Turkmenistan", "TM");
        countries.put("Turks and Caicos Islands", "TC");
        countries.put("Tuvalu", "TV");
        countries.put("U.S. Minor Outlying Islands", "UM");
        countries.put("U.S. Miscellaneous Pacific Islands", "PU");
        countries.put("U.S. Virgin Islands", "VI");
        countries.put("Uganda", "UG");
        countries.put("Ukraine", "UA");
        countries.put("Union of Soviet Socialist Republics", "SU");
        countries.put("United Arab Emirates", "AE");
        countries.put("United Kingdom", "GB");
        countries.put("United States", "US");
        countries.put("Unknown or Invalid Region", "ZZ");
        countries.put("Uruguay", "UY");
        countries.put("Uzbekistan", "UZ");
        countries.put("Vanuatu", "VU");
        countries.put("Vatican City", "VA");
        countries.put("Venezuela", "VE");
        countries.put("Vietnam", "VN");
        countries.put("Wake Island", "WK");
        countries.put("Wallis and Futuna", "WF");
        countries.put("Western Sahara", "EH");
        countries.put("Yemen", "YE");
        countries.put("Zambia", "ZM");
        countries.put("Zimbabwe", "ZW");
        countries.put("Åland Islands", "AX");
        
        return countries;
    }
}
