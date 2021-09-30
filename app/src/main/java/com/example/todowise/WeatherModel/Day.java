package com.example.todowise.WeatherModel;

import com.google.gson.annotations.SerializedName;

public class Day {
    @SerializedName("maxtemp_c")
    private String maxtemp_c;
    @SerializedName("mintemp_c")
    private String mintemp_c;
    @SerializedName("maxwind_kph")
    private String maxwind_kph;
    @SerializedName("condition")
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getMaxtemp_c() {
        return maxtemp_c;
    }

    public void setMaxtemp_c(String maxtemp_c) {
        this.maxtemp_c = maxtemp_c;
    }

    public String getMintemp_c() {
        return mintemp_c;
    }

    public void setMintemp_c(String mintemp_c) {
        this.mintemp_c = mintemp_c;
    }

    public String getMaxwind_kph() {
        return maxwind_kph;
    }

    public void setMaxwind_kph(String maxwind_kph) {
        this.maxwind_kph = maxwind_kph;
    }
}
