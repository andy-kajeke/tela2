package com.planetsystems.tela.data.timetble;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SyncTimeTableDao {
    @Insert
    void inertSyncTime(SyncTimeTable syncTimeTable);

    @Query("SELECT * FROM " + SyncTimeTableConstant.TABLE_NAME)
    LiveData<List<SyncTimeTable>> getSyncTimeBables();
}
