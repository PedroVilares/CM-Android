package com.example.hw2;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2.datamodel.City;
import com.example.hw2.datamodel.Weather;
import com.example.hw2.datamodel.WeatherType;
import com.example.hw2.network.CityResultsObserver;
import com.example.hw2.network.ForecastForACityResultsObserver;
import com.example.hw2.network.IpmaWeatherClient;
import com.example.hw2.network.WeatherTypesResultsObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstRecyclerAdapter extends RecyclerView.Adapter<FirstRecyclerAdapter.FirstViewHolder> {
    private String[] data1;
    private Context context;
    private boolean isFragmentDisplayed = false;

    private IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;
    private HashMap<Integer, WeatherType> weatherDescriptions;

    FirstRecyclerAdapter(Context cnt, String[] cities) {
        context = cnt;
        data1 = cities;
    }

    @NonNull
    @Override
    public FirstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.first_recycler_item,parent,false);

        return new FirstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstViewHolder holder, final int position) {
        holder.city.setText(data1[position]);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isFragmentDisplayed) {

                    callWeatherForecastForACityStep1(data1[position],v);

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return data1.length;
    }

    static class FirstViewHolder extends RecyclerView.ViewHolder {

        TextView city;
        LinearLayout mainLayout;

        FirstViewHolder(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.city_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

    private void chooseScreenSize(Bundle bundle,View view) {
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Log.i("msg","Normal sized screen");
            displayFragment(view,bundle);
        } else {
            Log.i("msg","Other");
            displayFragmentTablet(bundle,view);
        }
    }

    //FRAGMENT BUSINESS
    private void displayFragment(View view, Bundle bundle) {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        //Instantiating the fragment
        FragmentForecast fragmentForecast = FragmentForecast.newInstance();
        //passing bundle to fragment
        fragmentForecast.setArguments(bundle);
        //
        fragmentForecast.show(activity.getSupportFragmentManager(), "Weather Fragment");
    }

    public void displayFragmentTablet(Bundle bundle,View view){
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        FragmentForecastTablet fragmentForecastTablet = FragmentForecastTablet.newInstance();
        fragmentForecastTablet.setArguments(bundle);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_1,fragmentForecastTablet).addToBackStack(null).commit();
    }

    //IPMA API BUSINESS
    private void callWeatherForecastForACityStep1(final String city, final View view) {

        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                FirstRecyclerAdapter.this.weatherDescriptions = descriptorsCollection;
                callWeatherForecastForACityStep2( city,view);
            }
            @Override
            public void onFailure(Throwable cause) {
                Log.e("msg","Failed to get weather conditions!");
                Toast toast = Toast.makeText(context,"Sem ligação à internet",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    private void callWeatherForecastForACityStep2(final String city, final View view) {
        client.retrieveCitiesList(new CityResultsObserver() {

            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                FirstRecyclerAdapter.this.cities = citiesCollection;
                City cityFound = cities.get(city);
                if( null != cityFound) {
                    int locationId = cityFound.getGlobalIdLocal();
                    callWeatherForecastForACityStep3(locationId,view,city);
                }
            }

            @Override
            public void onFailure(Throwable cause) {
                Log.e("msg","Failed to get cities list!");
            }
        });
    }

    private void callWeatherForecastForACityStep3(int localId, final View view, final String city) {
        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                final CharSequence txt = "Clicked " + city;
                Bundle bundle = new Bundle();
                bundle.putString("city",city);
                int i= 0;
                for (Weather day : forecast) {
                    ArrayList<String> days = new ArrayList<>();
                    double tmax = day.getTMax();
                    double tmin = day.getTMin();
                    double preci = day.getPrecipitaProb();
                    int windSp = day.getClassWindSpeed();
                    String date = day.getForecastDate();
                    String windDir = day.getPredWindDir();

                    days.add(String.valueOf(tmax));
                    days.add(String.valueOf(tmin));
                    days.add(String.valueOf(preci));
                    days.add(String.valueOf(windSp*5));
                    days.add(String.valueOf(date));
                    days.add(String.valueOf(windDir));

                    String dayNum = "day"+String.valueOf(i);
                    bundle.putStringArrayList(dayNum,days);
                    i++;
                }

                //bundle.("forecast",forecast.toArray());

                //TODO: no fragment, descodificar o forecast

                chooseScreenSize(bundle,view);

                Toast toast = Toast.makeText(context,txt,Toast.LENGTH_SHORT);
                toast.show();
            }
            @Override
            public void onFailure(Throwable cause) {
                Log.e("msg","erro3");
            }
        });

    }


}
