package com.example.todowise.WeatherModel;

import com.google.gson.annotations.SerializedName;

public class Condition {
    @SerializedName("text")
    private String text;
    @SerializedName("icon")
    private String icon;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
