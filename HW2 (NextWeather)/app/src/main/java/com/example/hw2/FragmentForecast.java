package com.example.hw2;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentForecast extends DialogFragment {

    private ArrayList<ArrayList> days = new ArrayList<ArrayList>();
    private RecyclerView secondRecycler;

    public FragmentForecast() {
        // Required empty public constructor
    }

    static FragmentForecast newInstance() {
        return new FragmentForecast();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v= Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(R.layout.fragment_forecast, null, false);

        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.getContext().getTheme().applyStyle(R.style.MyAlertDialog, true);
        b.setView(v);


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
        return b.create();
    }
}
