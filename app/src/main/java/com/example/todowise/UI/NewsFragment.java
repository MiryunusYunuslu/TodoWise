package com.example.todowise.UI;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todowise.Adapter.NewsListAdapter;
import com.example.todowise.NewsModel.ListModel;
import com.example.todowise.R;
import com.example.todowise.ViewModel.NewsListViewModel;

import java.util.ArrayList;

public class NewsFragment extends Fragment {
    private ArrayList<ListModel> movieModelList;
    private NewsListAdapter movieListAdapter;
    private RecyclerView recyclerView;

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        NewsListViewModel.context = getContext();
        initilazie(view);
        return view;
    }

    private void initilazie(View view) {
        recyclerView = view.findViewById(R.id.recyclerView123);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        processOfGettingNews();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void processOfGettingNews() {
        NewsListViewModel viewmodel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        viewmodel.makeApiCall();
        viewmodel.getMovieList().observe(getViewLifecycleOwner(), new Observer<ArrayList<ListModel>>() {
            @Override
            public void onChanged(ArrayList<ListModel> listModels) {
                if (listModels != null) {
                    movieListAdapter.setMovielist(listModels);
                    movieModelList = listModels;
                    movieListAdapter.notifyDataSetChanged();
                }
            }
        });
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        movieListAdapter = new NewsListAdapter(getContext(), movieModelList);
        recyclerView.setAdapter(movieListAdapter);
        movieListAdapter.notifyDataSetChanged();
    }
}
//