package com.example.todowise.UI;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.todowise.Database.MainData;
import com.example.todowise.Database.RoomDataBase;
import com.example.todowise.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
public class AddFragment extends Fragment {
    private EditText editTextBasic, editTextUnderBasic;
    @SuppressLint("StaticFieldLeak")
    public  static TextView editTime, editDate;
    private RoomDataBase dataBase;
    private int hour;
    private int minute1;
    private int getDay, getMonth, getYear;
    private SharedPreferences sharedPreferences;
    @SuppressLint("StaticFieldLeak")
    public static MeowBottomNavigation meowBottomNavigation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedPreferences = requireActivity().getSharedPreferences("idCalendar", Context.MODE_PRIVATE);
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });
        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime();
            }
        });
        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                if (item.getId() == 2) {
                    saveButtonClickListener();
                } else {
                    System.out.println(1);
                }
            }
        });
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

            }
        });
    }

    private void getDate() {
        DatePickerDialog datePickerDialog;
        Calendar calendar = Calendar.getInstance();
        int dayNow = calendar.get(Calendar.DAY_OF_MONTH);
        int yearNow = calendar.get(Calendar.YEAR);
        int monthNow = calendar.get(Calendar.MONTH);
        //  System.out.println(monthNow);
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                getDay = dayOfMonth;
                getMonth = month + 1;
                getYear = year;
            }
        }, yearNow, monthNow, dayNow);
        datePickerDialog.show();
    }

    public void getTime() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                minute1 = minute;
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                AddFragment.editTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute1));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), AlertDialog.THEME_HOLO_LIGHT, onTimeSetListener, hour, minute1, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
    private void initialize(View view) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        editTextBasic = (EditText) view.findViewById(R.id.editTextBasic);
        editTextUnderBasic = (EditText) view.findViewById(R.id.edittextUnderBasic);
        editDate = view.findViewById(R.id.editDate);
        editTime = view.findViewById(R.id.editTime1);
        dataBase = RoomDataBase.getInstance(getContext());
    }
    public void saveButtonClickListener() {
        int calenndarId = sharedPreferences.getInt("idCalendar", 0);
        String basicText = editTextBasic.getText().toString();
        String underBasicText = editTextUnderBasic.getText().toString();
        if (!TextUtils.isEmpty(basicText) && !TextUtils.isEmpty(underBasicText) && !editTime.getText().equals("Click for time") && !editDate.getText().equals("Click for date")) {
            List<Integer> timeList = new ArrayList<>();
            timeList.add(getDay);
            timeList.add(getMonth);
            timeList.add(getYear);
            timeList.add(hour);
            timeList.add(minute1);
            MainData mainData = new MainData(basicText, timeList, underBasicText);
            dataBase.dao().insert(mainData);
            Toasty.success(requireContext(), "Your data is saved succesfully", Toasty.LENGTH_SHORT).show();
            sharedPreferences.edit().putInt("idCalendar", calenndarId + 1).apply();
        } else {
            Toasty.info(requireContext(), "Please Fill All Lines", Toasty.LENGTH_SHORT).show();
        }
    }



}