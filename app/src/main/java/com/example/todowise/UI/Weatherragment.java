package com.example.todowise.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.todowise.Adapter.WeatherAdapter;
import com.example.todowise.Network.GetInfo;
import com.example.todowise.R;
import com.example.todowise.WeatherModel.Forecastday;
import com.example.todowise.WeatherModel.WeatherModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
public class Weatherragment extends Fragment {
    private String baseUrl = "https://api.weatherapi.com/v1/";
    private Retrofit retrofit;
    private GetInfo getInfo;
    private String api = "d73309d0cd79410b98b193723212007";
    private String city, imageurl, hum, pres, feel, typeweather, q="auto:ip";
    private TextView cityname, tempurature, humidity, pressure, feellike, weathertype;
    private ImageView weatherimage, searchcountry;
    private double temp;
    private WeatherModel weatherModel;
    private ArrayList<Forecastday> forecastdays, forecastretrofit;
    private RecyclerView recyclerView;
    private ImageView thinImage;
   // private EditText searcheditttex;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weatherragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        retrofitloader(q);
    }

    private void initialize(View view) {
        cityname = view.findViewById(R.id.searchcityname);
        tempurature = view.findViewById(R.id.searchtempurature);
        humidity = view.findViewById(R.id.searchhumidity);
        pressure = view.findViewById(R.id.searchpressure);
        feellike = view.findViewById(R.id.searchfeellike);
        weathertype = view.findViewById(R.id.searchweathertype);
        weatherimage = view.findViewById(R.id.searchimageweather);
        recyclerView = view.findViewById(R.id.searchrecyclerview);
        thinImage=view.findViewById(R.id.thinImageInWeather);
    }

    private void retrofitloader(String q) {
        Gson gsonBuilder = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gsonBuilder)).build();
        getInfo = retrofit.create(GetInfo.class);
        Call<WeatherModel> call = getInfo.callback("forecast.json", api, q, 5);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {

                    city = response.body().getLocation().getCountry() + "," + response.body().getLocation().getName();
                    System.out.println(city);
                    temp = response.body().getCurrent().getTempC();
                    imageurl = response.body().getCurrent().getCondition().getIcon();
                    hum = String.valueOf((Integer.valueOf(response.body().getCurrent().getHumidity())));
                    pres = String.valueOf(response.body().getCurrent().getPressureMb());
                    feel = String.valueOf(response.body().getCurrent().getFeelslike_c());
                    typeweather = response.body().getCurrent().getCondition().getText();
                    forecastretrofit = response.body().getForecast().getForecastday();
                    setValues();
                    sendDatatoAdapter();
                    thinImage.setVisibility(View.VISIBLE);
                } else {
                    Toasty.error(getContext(),"No internet connection",Toasty.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Toasty.error(getContext(),"No internet connection",Toasty.LENGTH_SHORT).show();
            }
        });
    }

    private void sendDatatoAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        WeatherAdapter adapter = new WeatherAdapter(getContext(), forecastretrofit);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    public void setValues() {
        cityname.setText(city);
        tempurature.setText(String.valueOf(temp));
        Glide.with(requireContext()).load("https:" + imageurl).into(weatherimage);
        humidity.setText("Humidity: " + hum);
        pressure.setText("Pressure: " + pres);
        feellike.setText("Feels like " + feel);
        weathertype.setText(typeweather);
    }


}

