package com.example.todowise.ViewModel;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todowise.Network.ApiService;
import com.example.todowise.Network.RetroInstance;
import com.example.todowise.NewsModel.ListModel;
import com.example.todowise.NewsModel.MovieModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@RequiresApi(api = Build.VERSION_CODES.O)
public class NewsListViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<ListModel>> movieList;
    private String q = "top-headlines";
    static String currentDate;
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    public NewsListViewModel() {
        movieList = new MutableLiveData<>();
    }
    public MutableLiveData<ArrayList<ListModel>> getMovieList() {
        return movieList;
    }
    public void setQ(String q) {
        this.q = q;
    }
    public void makeApiCall() {
        ApiService apiService = RetroInstance.getRetrofit().create(ApiService.class);
        Call<MovieModel> call = apiService.callApi(q, currentDate, "publishedAt", "02a50775a2fc445bb056fae4a9950bd2", "en");
        call.enqueue(new Callback<MovieModel>() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    movieList.postValue(response.body().getArticles());
                } else {
                    Toasty.error(context, "No internet connection", Toasty.LENGTH_SHORT).show();
                }
            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Toasty.error(context, "No internet connection", Toasty.LENGTH_SHORT).show();
            }
        });
    }

    static {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime currenttime = LocalDateTime.now();
        currentDate = formatter.format(currenttime);
        System.out.println(currentDate);
    }
}