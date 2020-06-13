package com.example.hw2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondRecyclerAdapter extends RecyclerView.Adapter<SecondRecyclerAdapter.SecondViewHolder> {

    private ArrayList<ArrayList> data1;
    private Context context;
    private String cityT;
    private ArrayList tmax = new ArrayList();
    private ArrayList tmin = new ArrayList();
    private ArrayList rain = new ArrayList();
    private ArrayList windS = new ArrayList();
    private ArrayList windD = new ArrayList();
    private ArrayList date = new ArrayList();



    //TODO: fazer array com as variáveis metereológicas (um array para o tmax, um array para o tmin, etc.) e depois percorrer com o position

    SecondRecyclerAdapter(Context cnt, ArrayList<ArrayList> days, String city){
        context = cnt;
        cityT = city;
        data1 = days;
        for(ArrayList day : days){
            tmax.add(day.get(0));
            tmin.add(day.get(1));
            rain.add(day.get(2));
            windS.add(day.get(3));
            date.add(day.get(4));
            windD.add(day.get(5));
        }
    }

    static class SecondViewHolder extends RecyclerView.ViewHolder {

        TextView cityText,tmaxText,tminText,dateText,rainText,windSpeedText,windDirectionText;

        SecondViewHolder(@NonNull View itemView) {
            super(itemView);

            cityText = itemView.findViewById(R.id.cityFragmentText);
            tmaxText  = itemView.findViewById(R.id.tmaxFragmentText);
            tminText = itemView.findViewById(R.id.tminFragmentText);
            dateText = itemView.findViewById(R.id.dayFragmentText);
            rainText = itemView.findViewById(R.id.rainFragmentText);
            windSpeedText = itemView.findViewById(R.id.windSpeedFragmentText);
            windDirectionText = itemView.findViewById(R.id.windDirectionFragmentText);
        }
    }

    @NonNull
    @Override
    public SecondViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.second_recycler_item,parent,false);
        return new SecondViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondViewHolder holder, int position) {

        holder.cityText.setText(cityT);
        holder.tmaxText.setText(String.format("%sº", tmax.get(position)));
        holder.tminText.setText(String.format("%sº", tmin.get(position)));
        holder.rainText.setText(String.format("%s",rain.get(position)));
        holder.windSpeedText.setText(String.format("%s",windS.get(position)));
        holder.dateText.setText(String.format("%s",date.get(position)));
        holder.windDirectionText.setText(String.format("%s", windD.get(position)));
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }


}
