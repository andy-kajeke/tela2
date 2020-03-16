package com.planetsystems.tela.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.planetsystems.tela.constants.SyncClockInNames;
import com.planetsystems.tela.enties.SyncClockIn;

import java.util.List;

@Dao
public interface SyncClockInDao {

    @Insert
    void insertClockIn(SyncClockIn clockIn);

    @Query("SELECT * FROM " + SyncClockInNames.TABLE_NAME)
    LiveData<List<SyncClockIn>> getAllClockIn();
}
