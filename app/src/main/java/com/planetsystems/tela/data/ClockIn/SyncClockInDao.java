package com.planetsystems.tela.data.ClockIn;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SyncClockInDao {

    @Insert
    void insertClockIn(SyncClockIn clockIn);

    @Query("SELECT * FROM " + SyncClockInTableConstants.TABLE_NAME)
    LiveData<List<SyncClockIn>> getAllClockIn();

    @Insert
    void syncClockInTeacherWithID(SyncClockIn clockIn);

    @Query("SELECT * FROM " + SyncClockInTableConstants.TABLE_NAME)
    List<SyncClockIn> getSyncClockInsForBackUp();

    @Query(
            "SELECT * FROM "
             + SyncClockInTableConstants.TABLE_NAME
             + " WHERE "
             + SyncClockInTableConstants.COLUMN_DAY
             + " = :date")
    LiveData<List<SyncClockIn>> getSyncClockInByDate(String date);

    @Query("SELECT * FROM "
            + SyncClockInTableConstants.TABLE_NAME
            + " WHERE "
            + SyncClockInTableConstants.COLUMN_CLOCK_IN_DATE
            + " =:date"
            + " AND "
            + SyncClockInTableConstants.COLUMN_EMPLOYEE_NUMBER
            + " =:employeeNumber"
    )
    List<SyncClockIn> getSyncClockInByEmployeeIDAndDate(String employeeNumber, String date);

    @Query(
            "SELECT * FROM "
            + SyncClockInTableConstants.TABLE_NAME
            + " WHERE "
            + SyncClockInTableConstants.COLUMN_IS_UPLOADED
            + " =: false"
    )
    List<SyncClockIn> getSyncClockInForBackUp();

    @Update
    void updateSyncClockIn(SyncClockIn syncClockIn);
}
