package com.example.hw2.network;

import com.example.hw2.datamodel.City;

import java.util.HashMap;

public interface  CityResultsObserver {
    void receiveCitiesList(HashMap<String, City> citiesCollection);
    void onFailure(Throwable cause);
}
