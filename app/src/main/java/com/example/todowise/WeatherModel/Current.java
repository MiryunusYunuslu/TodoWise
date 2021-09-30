package com.example.todowise.WeatherModel;

import com.google.gson.annotations.SerializedName;

public class Current {
    @SerializedName("lastupdated")
    private String lastUpdated;
    @SerializedName("temp_c")
    private double tempC;
    @SerializedName("condition")
    private Condition condition;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("pressure_mb")
    private double pressureMb;
    @SerializedName("feelslike_c")
    private double feelslike_c;

    public double getFeelslike_c() {
        return feelslike_c;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public double getTempC() {
        return tempC;
    }

    public Condition getCondition() {
        return condition;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getPressureMb() {
        return pressureMb;
    }
}
