package com.example.todowise.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "EntityData")
public class MainData {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "basicTitle")
    private String basicTitle;
    @ColumnInfo(name = "time")
    private List<Integer> timeList;
    @ColumnInfo(name = "underBasicTitle")
    private String underBasicTitle;

    public MainData(String basicTitle, List<Integer> timeList, String underBasicTitle) {
        this.basicTitle = basicTitle;
        this.timeList = timeList;
        this.underBasicTitle = underBasicTitle;
    }

    public List<Integer> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Integer> timeList) {
        this.timeList = timeList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBasicTitle() {
        return basicTitle;
    }

    public void setBasicTitle(String basicTitle) {
        this.basicTitle = basicTitle;
    }

    public String getUnderBasicTitle() {
        return underBasicTitle;
    }

    public void setUnderBasicTitle(String underBasicTitle) {
        this.underBasicTitle = underBasicTitle;
    }
}
