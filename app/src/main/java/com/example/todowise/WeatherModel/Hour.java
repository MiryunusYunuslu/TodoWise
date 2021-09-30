package com.example.todowise.WeatherModel;

import com.google.gson.annotations.SerializedName;

public class Hour {
    @SerializedName("time")
    private String time;
    @SerializedName("temp_c")
    private String temp_c;
    @SerializedName("condition")
     Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @SerializedName("wind_kph")
    private String wind_kph;
    @SerializedName("humidity")
    private String humidty;
    @SerializedName("will_it_rain")
    private String will_it_rain;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }

    public String getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(String wind_kph) {
        this.wind_kph = wind_kph;
    }

    public String getHumidty() {
        return humidty;
    }

    public void setHumidty(String humidty) {
        this.humidty = humidty;
    }

    public String getWill_it_rain() {
        return will_it_rain;
    }

    public void setWill_it_rain(String will_it_rain) {
        this.will_it_rain = will_it_rain;
    }

}
