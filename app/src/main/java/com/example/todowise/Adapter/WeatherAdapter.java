package com.example.todowise.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.todowise.R;
import com.example.todowise.WeatherModel.Forecastday;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Forecastday> forecastdays;


    public WeatherAdapter(Context context, ArrayList<Forecastday> forecastdays) {
        this.context = context;
        this.forecastdays = forecastdays;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.forecast_weather, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.temp.setText(forecastdays.get(0).getHour().get(position).getTemp_c());
        Glide.with(context).load("https:" + forecastdays.get(0).getHour().get(position).getCondition().getIcon()).into(holder.imageView);
        holder.timed.setText(forecastdays.get(0).getHour().get(position).getTime().substring(10, 16));
        holder.windspeed.setText(forecastdays.get(0).getHour().get(position).getWind_kph() + "km/h");
    }

    @Override
    public int getItemCount() {
        return forecastdays.get(0).getHour().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView temp, timed, windspeed;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            temp = itemView.findViewById(R.id.tempuraturebelowtime);
            timed = itemView.findViewById(R.id.timeshow);
            imageView = itemView.findViewById(R.id.imageweatherbelowtemp);
            windspeed = itemView.findViewById(R.id.windspeed);
        }
    }
}
