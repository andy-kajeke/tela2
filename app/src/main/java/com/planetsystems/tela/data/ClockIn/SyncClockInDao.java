package com.planetsystems.tela.data.ClockIn;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SyncClockInDao {

    @Insert
    void insertClockIn(SyncClockIn clockIn);

    @Query("SELECT * FROM " + SyncClockInConstants.TABLE_NAME)
    LiveData<List<SyncClockIn>> getAllClockIn();

    @Insert
    void syncClockInTeacherWithID(SyncClockIn clockIn);
}
