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
    void insertClockOutTeacher(SyncClockOut syncTeacher);

    @Update
    void update(SyncClockOut syncTeachers);

    @Delete
    void delete(SyncClockOut syncTeachers);

    @Query("SELECT * FROM " + SyncClockOutTableConstant.TABLE_NAME)
    List<SyncClockOut> getSyncClockInsForBackUp();

    @Query("SELECT * FROM " + SyncClockOutTableConstant.TABLE_NAME+ " WHERE " + SyncClockOutTableConstant.DATE_COLUMN_NAME + " = :date")
    LiveData<List<SyncClockOut>> getSyncClockOutsByDate(String date);

    @Query("SELECT * FROM " + SyncClockOutTableConstant.TABLE_NAME + " WHERE "
            + SyncClockOutTableConstant.EMPLOYEE_NUMBER_COLUMN_NAME + " =:employeeId AND " + SyncClockOutTableConstant.DATE_COLUMN_NAME + " =:date")
    List<SyncClockOut> getSyncClockOutByEmployeeIdAndDate(String employeeId, String date);

}
