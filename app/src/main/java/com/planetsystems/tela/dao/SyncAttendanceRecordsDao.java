package com.planetsystems.tela.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;
import com.planetsystems.tela.enties.SyncAttendanceRecords;
import com.planetsystems.tela.enties.SyncClockOuts;

import java.util.List;

@Dao
public interface SyncAttendanceRecordsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNew(SyncAttendanceRecords syncAttendanceRecords);

    @Update
    void update(SyncAttendanceRecords syncAttendanceRecords);

    @Delete
    void delete(SyncAttendanceRecords syncAttendanceRecords);

    @Query("SELECT * FROM " + SyncTableConstants.SyncClockOuts)
    LiveData<List<SyncAttendanceRecords>> getAllRecords();
}
