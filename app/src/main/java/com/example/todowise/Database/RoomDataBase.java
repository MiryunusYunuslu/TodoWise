package com.example.todowise.Database;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.todowise.DatabaseConverter.Converters;
@Database(entities = {MainData.class}, version = 4, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class RoomDataBase extends RoomDatabase {
    private static RoomDataBase roomDataBase;
    private static final String Database_Name = "todoDatabase";
    public synchronized static RoomDataBase getInstance(Context context) {
        if (roomDataBase == null) {
            roomDataBase = Room.databaseBuilder(context, RoomDataBase.class, Database_Name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return roomDataBase;
    }

    public abstract Dao dao();
}