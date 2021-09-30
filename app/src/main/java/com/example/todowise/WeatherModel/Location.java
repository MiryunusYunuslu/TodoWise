package com.example.todowise.WeatherModel;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("name")
    private String name;
    @SerializedName("region")
    private String region;
    @SerializedName("country")
    private String country;
    public String getCountry() {
        return country;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
