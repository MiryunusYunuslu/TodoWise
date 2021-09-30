package com.example.todowise.WeatherModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Forecastday {
    @SerializedName("date")
    private  String date;
    @SerializedName("day")
    private Day day;
    @SerializedName("astro")
    private Astro astro;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public ArrayList<Hour> getHour() {
        return hour;
    }

    public void setHour(ArrayList<Hour> hour) {
        this.hour = hour;
    }

    @SerializedName("hour")
    private ArrayList<Hour> hour;


}
