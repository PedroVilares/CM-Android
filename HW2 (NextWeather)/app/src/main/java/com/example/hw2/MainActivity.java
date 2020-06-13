package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    String[] cities;
    RecyclerView firstRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cities = getResources().getStringArray(R.array.citiesArray);
        firstRecycler = findViewById(R.id.citiesRecycler);

        FirstRecyclerAdapter firstRecyclerAdapter = new FirstRecyclerAdapter(this, cities);
        firstRecycler.setAdapter(firstRecyclerAdapter);
        firstRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
