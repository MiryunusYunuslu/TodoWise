package com.example.todowise.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todowise.Adapter.MainAdapter;
import com.example.todowise.Database.MainData;
import com.example.todowise.Database.RoomDataBase;
import com.example.todowise.R;

import java.time.LocalDate;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private MainData mainData;
    private List<MainData> listData;
    private TextView day, month, year, week;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        setCurrentTime();
        setValuestoRecyclerView();
        checkList();
    }

    private void checkList() {
        if (listData.size() == 0) {
            Toasty.info(requireContext(), "Your list is empty", Toasty.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void setValuestoRecyclerView() {
        RoomDataBase dataBase = RoomDataBase.getInstance(getContext());
        listData = dataBase.dao().getAllData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        MainAdapter mainAdapter = new MainAdapter(listData, getContext());
        recyclerView.setAdapter(mainAdapter);
        recyclerView.startLayoutAnimation();
    }

    private void initialize(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        day = view.findViewById(R.id.dayOfToday1);
        year = view.findViewById(R.id.yearOfToday);
        month = view.findViewById(R.id.monthOfToday);
        week = view.findViewById(R.id.weekDay);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setCurrentTime() {
        //Calendar calendar = Calendar.getInstance();
        //String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String dayOfWeek1 = LocalDate.now().getDayOfWeek().name();
        String month1 = LocalDate.now().getMonth().name();
        int day1 = LocalDate.now().getDayOfMonth();
        int year1 = LocalDate.now().getYear();
        day.setText(String.valueOf(day1));
        year.setText(String.valueOf(year1));
        month.setText(month1);
        week.setText(dayOfWeek1);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_layout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteTask) {
            SharedPreferences sharedPreferences1 = requireContext().getSharedPreferences("channelText1", Context.MODE_PRIVATE);
            sharedPreferences1.edit().putInt("channelText", 0).apply();
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("idCalendar", Context.MODE_PRIVATE);
            sharedPreferences.edit().putInt("idCalendar", 0).apply();
            RoomDataBase roomDataBase = RoomDataBase.getInstance(getContext());
            roomDataBase.dao().reset(listData);
            listData.clear();
            setValuestoRecyclerView();
            Toasty.info(requireContext(), "Your list is empty", Toasty.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }
}
