package com.example.todowise.WeatherModel;


import com.google.gson.annotations.SerializedName;

public class Astro {
    @SerializedName("sunrise")
    private String sunrise;
    public String getSunrise() {
        return sunrise;
    }
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
    public String getSunset() {
        return sunset;
    }
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
    @SerializedName("sunset")
    private String sunset;

}
