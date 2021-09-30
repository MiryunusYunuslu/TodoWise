package com.example.todowise.Database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    void insert(MainData maindata);

    @Delete
    void delete(MainData mainData);

    //@Query("UPDATE EntityData SET basicTitle=:basic,underBasicTitle=:underBasic,Time=:time WHERE ID=:id")
    // void update(String basic,String underBasic, String time, int id);
    @Query("SELECT * FROM  EntityData ORDER BY ID DESC")
    List<MainData> getAllData();

    @Delete
    void reset(List<MainData> mainData);
    @Query("UPDATE EntityData SET basicTitle=:title WHERE ID=:id")
    void updateTitle(String title, int id);

    @Query("UPDATE entitydata SET underBasicTitle=:underTitle WHERE ID=:id")
    void updateUnderTitle(String underTitle, int id);
}
