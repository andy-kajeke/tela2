package com.planetsystems.tela.data.attendance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;

import java.util.List;

@Dao
public interface SyncAttendanceRecordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNew(SyncAttendanceRecord syncAttendanceRecord);

    @Update
    void update(SyncAttendanceRecord syncAttendanceRecord);

    @Delete
    void delete(SyncAttendanceRecord syncAttendanceRecord);

    @Query("SELECT * FROM " + SyncTableConstants.SyncClockOuts)
    LiveData<List<SyncAttendanceRecord>> getAllRecords();
}
