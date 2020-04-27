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
            + SyncClockInTableConstants.COLUMN_DAY
            + " =:day"
            + " AND "
            + SyncClockInTableConstants.COLUMN_EMPLOYEE_ID
            + " =:employeeID"
    )
    List<SyncClockIn> getSyncClockInByEmployeeIDAndDay(String employeeID, String day);
}
