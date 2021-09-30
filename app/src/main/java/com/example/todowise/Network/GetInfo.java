package com.example.todowise.Network;
import com.example.todowise.WeatherModel.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetInfo {
    @GET("{type}")
    Call <WeatherModel> callback(
            @Path("type") String type,
            @Query("key") String key,
            @Query("q") String q,
            @Query("day") int day
     );
}
