package com.example.todowise.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todowise.Adapter.AdapterProfile;
import com.example.todowise.R;

public class ProfileFragment extends Fragment {
    private RecyclerView recyclerView;
    private final int[] images = {R.drawable.news, R.drawable.weather12, R.drawable.settings12, R.drawable.rate1};
    private final String[] namesOfImagse = {"News", "Weather", "Settings", "Rate Us"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        sendValuesToAdapter();
    }

    private void sendValuesToAdapter() {
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(linearLayoutManager);
        AdapterProfile adapterProfile = new AdapterProfile(getContext(), namesOfImagse, images);
        recyclerView.setAdapter(adapterProfile);
        adapterProfile.notifyDataSetChanged();
    }

    private void initialize(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_profile);
    }
}