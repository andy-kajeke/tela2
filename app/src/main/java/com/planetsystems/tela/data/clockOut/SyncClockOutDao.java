package com.planetsystems.tela.data.clockOut;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;

import java.text.SimpleDateFormat;
import java.util.List;

@Dao
public interface SyncClockOutDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void clockOutTeacher(SyncClockOut syncTeacher);

    @Update
    void update(SyncClockOut syncTeachers);

    @Delete
    void delete(SyncClockOut syncTeachers);

    @Query("SELECT * FROM " + SyncClockOutTableConstant.TABLE_NAME)
    LiveData<List<SyncClockOut>> getClockOutTeachers();

    @Query("SELECT * FROM " + SyncClockOutTableConstant.TABLE_NAME+ " WHERE " + SyncClockOutTableConstant.DAY_COLUMN_NAME + " = :date")
    LiveData<List<SyncClockOut>> getSyncClockOutsByDate(String date);

}
