package com.planetsystems.tela.data.ClockIn;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SyncClockInDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void syncClockInTeacherWithID(SyncClockIn clockIn);

    @Query("SELECT * FROM " + SyncClockInTableConstants.TABLE_NAME)
    LiveData<List<SyncClockIn>> getAllClockIn();

    @Query(
            "SELECT * FROM "
             + SyncClockInTableConstants.TABLE_NAME
             + " WHERE "
             + SyncClockInTableConstants.COLUMN_CLOCK_IN_DATE
             + " = :date")
    LiveData<List<SyncClockIn>> getSyncClockInByDate(String date);

    @Query(
            "SELECT * FROM "
                    + SyncClockInTableConstants.TABLE_NAME
                    + " WHERE "
                    + SyncClockInTableConstants.COLUMN_CLOCK_IN_DATE
                    + " = :date")
    List<SyncClockIn> getSyncClockInByDateNotLiveData(String date);

    @Query("SELECT * FROM "
            + SyncClockInTableConstants.TABLE_NAME
            + " WHERE "
            + SyncClockInTableConstants.COLUMN_CLOCK_IN_DATE
            + " =:date"
            + " AND "
            + SyncClockInTableConstants.COLUMN_EMPLOYEE_NUMBER
            + " =:employeeNumber"
    )
    SyncClockIn getSyncClockInByEmployeeIDAndDate(String employeeNumber, String date);

    @Query(
            "SELECT * FROM "
            + SyncClockInTableConstants.TABLE_NAME
            + " WHERE "
            + SyncClockInTableConstants.COLUMN_IS_UPLOADED
            + " =:isUploaded"
    )
    List<SyncClockIn> getSyncClockInForBackUp(boolean isUploaded);

    @Update
    void updateSyncClockIn(SyncClockIn syncClockIn);

    @Query("SELECT * FROM "
            + SyncClockInTableConstants.TABLE_NAME
            + " WHERE "
            + SyncClockInTableConstants.COLUMN_CLOCK_IN_DATE
            + " =:date"
            + " AND "
            + SyncClockInTableConstants.COLUMN_FINGER_PRINT
            + " =:fingerPrint"
    )
    SyncClockIn getSynClickWithFingerprintAndDate(byte[] fingerPrint, String date);

    @Query("SELECT * FROM " + SyncClockInTableConstants.TABLE_NAME + " WHERE "
    + SyncClockInTableConstants.COLUMN_DAY + " =:date")
    List<SyncClockIn> getSyncClockInsByDate(String date);
}
