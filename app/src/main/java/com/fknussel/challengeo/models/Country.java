package com.fknussel.challengeo.models;

import java.util.HashMap;
import java.util.List;

public class Country {
    
    private String name;
    private String capital;
    private String region;
    private String subregion;
    private String nativeName;
    private HashMap<String, String> translations;
    private String alpha2Code;
    private String alpha3Code;
    private double population;
    private String demonym;
    private double area;
    private double gini;
    private double[] latlng;
    private List<String> timezones;
    private List<String> borders;
    private List<String> callingCodes;
    private List<String> topLevelDomain;
    private List<String> currencies;
    private List<String> languages;

    public Country(String name, String capital, String region, String subregion, String nativeName, HashMap<String, String> translations, String alpha2Code, String alpha3Code, int population, String demonym, double area, double gini, double[] latlng, List<String> timezones, List<String> borders, List<String> callingCodes, List<String> tld, List<String> currencies, List<String> languages) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.nativeName = nativeName;
        this.translations = translations;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.population = population;
        this.demonym = demonym;
        this.area = area;
        this.gini = gini;
        this.latlng = latlng;
        this.timezones = timezones;
        this.borders = borders;
        this.callingCodes = callingCodes;
        this.topLevelDomain = topLevelDomain;
        this.currencies = currencies;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public HashMap<String, String> getTranslations() {
        return translations;
    }

    public void setTranslations(HashMap<String, String> translations) {
        this.translations = translations;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getGini() {
        return gini;
    }

    public void setGini(double gini) {
        this.gini = gini;
    }

    public double[] getCoordinates() {
        return latlng;
    }
    
    public double getLat() {
        return latlng[0];
    }
    
    public double getLng() {
        return latlng[1];
    }

    public void setLat(double lat) {
        this.latlng[0] = lat;
    }

    public void setLng(double lng) {
        this.latlng[1] = lng;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
