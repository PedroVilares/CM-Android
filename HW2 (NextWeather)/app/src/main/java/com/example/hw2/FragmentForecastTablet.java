package com.example.hw2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentForecastTablet extends Fragment {

    private ArrayList<ArrayList> days = new ArrayList<ArrayList>();
    private RecyclerView secondRecycler;

    public FragmentForecastTablet() {
        // Required empty public constructor
    }

    static FragmentForecastTablet newInstance() {
        return new FragmentForecastTablet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_forecast_tablet, container, false);
        secondRecycler = v.findViewById(R.id.forecastRecycler);

        Bundle bundle = getArguments();
        assert bundle != null;
        String city = bundle.getString("city");
        ArrayList day1 = bundle.getStringArrayList("day0");
        ArrayList day2 = bundle.getStringArrayList("day1");
        ArrayList day3 = bundle.getStringArrayList("day2");
        ArrayList day4 = bundle.getStringArrayList("day3");
        ArrayList day5 = bundle.getStringArrayList("day4");
        assert day1 != null;

        days.add(day1);
        days.add(day2);
        days.add(day3);
        days.add(day4);
        days.add(day5);

        SecondRecyclerAdapter secondRecyclerAdapter = new SecondRecyclerAdapter(getContext(), days,city);
        secondRecycler.setAdapter(secondRecyclerAdapter);
        secondRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        // Inflate the layout for this fragment
        return v;
    }
}
