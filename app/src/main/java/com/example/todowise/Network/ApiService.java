package com.example.todowise.Network;

import com.example.todowise.NewsModel.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("everything")
    Call <MovieModel> callApi(
            @Query("q") String q,
            @Query("from") String from,
            @Query("sortBy") String sortBy,
            @Query("apiKey" ) String apiKey,
            @Query("language") String language
    );
}
