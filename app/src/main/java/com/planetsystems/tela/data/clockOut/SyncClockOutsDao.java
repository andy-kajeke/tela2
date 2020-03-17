package com.planetsystems.tela.data.clockOut;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;
import com.planetsystems.tela.data.clockOut.SyncClockOuts;

import java.util.List;

@Dao
public interface SyncClockOutsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNew(SyncClockOuts syncTeachers);

    @Update
    void update(SyncClockOuts syncTeachers);

    @Delete
    void delete(SyncClockOuts syncTeachers);

    @Query("SELECT * FROM " + SyncTableConstants.SyncClockOuts)
    LiveData<List<SyncClockOuts>> getAllRecords();
}
