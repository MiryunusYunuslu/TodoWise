package com.example.todowise.DatabaseConverter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<Integer> storedStringToMyObjects(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        return gson.fromJson(data, type);
    }

    @TypeConverter
    public static String myObjectsToStoredString(List<Integer> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }
}