package com.planetsystems.tela.data.timetable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SyncTimeTableDao {
    @Insert
    void insertSyncTimeTable(SyncTimeTable syncTimeTable);

    @Query("SELECT * FROM " + SyncTimeTableConstant.TABLE_NAME)
    LiveData<List<SyncTimeTable>> getSyncTimeTables();

}
