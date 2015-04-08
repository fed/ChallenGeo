package com.fknussel.challengeo.utils;

import com.fknussel.challengeo.models.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AppHelper {
    
    private static final String TAG = AppHelper.class.getSimpleName();
    
    public static final ArrayList<Country> listCountries = new ArrayList<>();

    // Key: Country name, Value: Alpha2Code
    public static final HashMap<String, String> mapCodes = new HashMap<>();

    // This list just holds the names for all of the countries (used in AutoCompleteTextView)
    // Used as a workaround till building a HashMapAdapter!!!
    public static final ArrayList<String> listNames = new ArrayList<>();

    // Flags Source
    public static final String FLAG_BASE_URL = "http://www.geonames.org/flags/x/";
    public static final String FLAG_DEFAULT_EXTENSION = ".gif";
    
    // Challenge Statistics
    public static int streak = 0;
    public static int livesLeft = 3;

    // Languages
    public static final HashMap<String, String> mapLanguages = new HashMap<>();
    
    public static void loadLanguages() {
        mapLanguages.put("AB", "Abkhazian");
        mapLanguages.put("AA", "Afar");
        mapLanguages.put("AF", "Afrikaans");
        mapLanguages.put("SQ", "Albanian");
        mapLanguages.put("AM", "Amharic");
        mapLanguages.put("AR", "Arabic");
        mapLanguages.put("HY", "Armenian");
        mapLanguages.put("AS", "Assamese");
        mapLanguages.put("AY", "Aymara");
        mapLanguages.put("AZ", "Azerbaijani");
        mapLanguages.put("BA", "Bashkir");
        mapLanguages.put("EU", "Basque");
        mapLanguages.put("BN", "Bengali, Bangla");
        mapLanguages.put("DZ", "Bhutani");
        mapLanguages.put("BH", "Bihari");
        mapLanguages.put("BI", "Bislama");
        mapLanguages.put("BR", "Breton");
        mapLanguages.put("BG", "Bulgarian");
        mapLanguages.put("MY", "Burmese");
        mapLanguages.put("BE", "Byelorussian");
        mapLanguages.put("KM", "Cambodian");
        mapLanguages.put("CA", "Catalan");
        mapLanguages.put("ZH", "Chinese");
        mapLanguages.put("CO", "Corsican");
        mapLanguages.put("HR", "Croatian");
        mapLanguages.put("CS", "Czech");
        mapLanguages.put("DA", "Danish");
        mapLanguages.put("NL", "Dutch");
        mapLanguages.put("EN", "English, American");
        mapLanguages.put("EO", "Esperanto");
        mapLanguages.put("ET", "Estonian");
        mapLanguages.put("FO", "Faeroese");
        mapLanguages.put("FJ", "Fiji");
        mapLanguages.put("FI", "Finnish");
        mapLanguages.put("FR", "French");
        mapLanguages.put("FY", "Frisian");
        mapLanguages.put("FF", "Fula");
        mapLanguages.put("GD", "Gaelic (Scots Gaelic)");
        mapLanguages.put("GL", "Galician");
        mapLanguages.put("KA", "Georgian");
        mapLanguages.put("DE", "German");
        mapLanguages.put("EL", "Greek");
        mapLanguages.put("KL", "Greenlandic");
        mapLanguages.put("GN", "Guarani");
        mapLanguages.put("GU", "Gujarati");
        mapLanguages.put("HA", "Hausa");
        mapLanguages.put("IW", "Hebrew");
        mapLanguages.put("HI", "Hindi");
        mapLanguages.put("HU", "Hungarian");
        mapLanguages.put("IS", "Icelandic");
        mapLanguages.put("IN", "Indonesian");
        mapLanguages.put("IA", "Interlingua");
        mapLanguages.put("IE", "Interlingue");
        mapLanguages.put("IK", "Inupiak");
        mapLanguages.put("GA", "Irish");
        mapLanguages.put("IT", "Italian");
        mapLanguages.put("JA", "Japanese");
        mapLanguages.put("JW", "Javanese");
        mapLanguages.put("KN", "Kannada");
        mapLanguages.put("KS", "Kashmiri");
        mapLanguages.put("KK", "Kazakh");
        mapLanguages.put("RW", "Kinyarwanda");
        mapLanguages.put("KY", "Kirghiz");
        mapLanguages.put("RN", "Kirundi");
        mapLanguages.put("KO", "Korean");
        mapLanguages.put("KU", "Kurdish");
        mapLanguages.put("LO", "Laothian");
        mapLanguages.put("LA", "Latin");
        mapLanguages.put("LV", "Latvian, Lettish");
        mapLanguages.put("LN", "Lingala");
        mapLanguages.put("LT", "Lithuanian");
        mapLanguages.put("MK", "Macedonian");
        mapLanguages.put("MG", "Malagasy");
        mapLanguages.put("MS", "Malay");
        mapLanguages.put("ML", "Malayalam");
        mapLanguages.put("MT", "Maltese");
        mapLanguages.put("MI", "Maori");
        mapLanguages.put("MR", "Marathi");
        mapLanguages.put("MO", "Moldavian");
        mapLanguages.put("MN", "Mongolian");
        mapLanguages.put("NA", "Nauru");
        mapLanguages.put("NE", "Nepali");
        mapLanguages.put("NO", "Norwegian");
        mapLanguages.put("OC", "Occitan");
        mapLanguages.put("OR", "Oriya");
        mapLanguages.put("OM", "Oromo, Afan");
        mapLanguages.put("PS", "Pashto, Pushto");
        mapLanguages.put("FA", "Persian");
        mapLanguages.put("PL", "Polish");
        mapLanguages.put("PT", "Portuguese");
        mapLanguages.put("PA", "Punjabi");
        mapLanguages.put("QU", "Quechua");
        mapLanguages.put("RM", "Rhaeto-Romance");
        mapLanguages.put("RO", "Romanian");
        mapLanguages.put("RU", "Russian");
        mapLanguages.put("SM", "Samoan");
        mapLanguages.put("SG", "Sangro");
        mapLanguages.put("SA", "Sanskrit");
        mapLanguages.put("SR", "Serbian");
        mapLanguages.put("SH", "Serbo-Croatian");
        mapLanguages.put("ST", "Sesotho");
        mapLanguages.put("TN", "Setswana");
        mapLanguages.put("SN", "Shona");
        mapLanguages.put("SD", "Sindhi");
        mapLanguages.put("SI", "Singhalese");
        mapLanguages.put("SS", "Siswati");
        mapLanguages.put("SK", "Slovak");
        mapLanguages.put("SL", "Slovenian");
        mapLanguages.put("SO", "Somali");
        mapLanguages.put("ES", "Spanish");
        mapLanguages.put("SU", "Sudanese");
        mapLanguages.put("SW", "Swahili");
        mapLanguages.put("SV", "Swedish");
        mapLanguages.put("TL", "Tagalog");
        mapLanguages.put("TG", "Tajik");
        mapLanguages.put("TA", "Tamil");
        mapLanguages.put("TT", "Tatar");
        mapLanguages.put("TE", "Tegulu");
        mapLanguages.put("TH", "Thai");
        mapLanguages.put("BO", "Tibetan");
        mapLanguages.put("TI", "Tigrinya");
        mapLanguages.put("TO", "Tonga");
        mapLanguages.put("TS", "Tsonga");
        mapLanguages.put("TR", "Turkish");
        mapLanguages.put("TK", "Turkmen");
        mapLanguages.put("TW", "Twi");
        mapLanguages.put("UK", "Ukrainian");
        mapLanguages.put("UR", "Urdu");
        mapLanguages.put("UZ", "Uzbek");
        mapLanguages.put("VI", "Vietnamese");
        mapLanguages.put("VO", "Volapuk");
        mapLanguages.put("CY", "Welsh");
        mapLanguages.put("WO", "Wolof");
        mapLanguages.put("XH", "Xhosa");
        mapLanguages.put("JI", "Yiddish");
        mapLanguages.put("YO", "Yoruba");
        mapLanguages.put("ZU", "Zulu");
    }

    // Random country generator
    public static String getRandomCountryName() {
        int size = AppHelper.listNames.size();
        int min = 0;
        int max = size - 1;
        Random rand = new Random();
        int random = rand.nextInt((max - min) + 1) + min;
        return AppHelper.listNames.get(random);
    }
}
