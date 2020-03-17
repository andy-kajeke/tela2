package com.planetsystems.tela.data.clockOut;

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
public interface SyncClockOutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNew(SyncClockOut syncTeachers);

    @Update
    void update(SyncClockOut syncTeachers);

    @Delete
    void delete(SyncClockOut syncTeachers);

    @Query("SELECT * FROM " + SyncTableConstants.SyncClockOuts)
    LiveData<List<SyncClockOut>> getAllRecords();
}
