package com.example.todowise.WeatherModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Forecast {
    @SerializedName("forecastday")
    private ArrayList<Forecastday>forecastday;

    public ArrayList<Forecastday> getForecastday() {
        return forecastday;
    }
    public void setForecast(ArrayList<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }
}
