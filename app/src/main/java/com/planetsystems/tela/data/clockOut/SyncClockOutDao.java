package com.planetsystems.tela.data.clockOut;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SyncClockOutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertClockOutTeacher(SyncClockOut syncTeacher);

    @Update
    void update(SyncClockOut syncTeachers);

    @Delete
    void delete(SyncClockOut syncTeachers);

    @Query("SELECT * FROM " + SyncClockOutTableConstant.TABLE_NAME + " WHERE " + SyncClockOutTableConstant.IS_UPLOADED_COLUMN_NAME + " =:isUploaded")
    List<SyncClockOut> getSyncClockOutForBackUp(boolean isUploaded);

    @Query("SELECT * FROM " + SyncClockOutTableConstant.TABLE_NAME+ " WHERE " + SyncClockOutTableConstant.DATE_COLUMN_NAME + " = :date")
    LiveData<List<SyncClockOut>> getSyncClockOutsByDate(String date);

    @Query("SELECT * FROM " + SyncClockOutTableConstant.TABLE_NAME + " WHERE "
            + SyncClockOutTableConstant.EMPLOYEE_NUMBER_COLUMN_NAME + " =:employeeId AND " + SyncClockOutTableConstant.DATE_COLUMN_NAME + " =:date")
    SyncClockOut getSyncClockOutByEmployeeIdAndDate(String employeeId, String date);

    @Query("SELECT * FROM " + SyncClockOutTableConstant.TABLE_NAME)
    LiveData<List<SyncClockOut>> getAllClockOuts();

    @Query("SELECT * FROM " + SyncClockOutTableConstant.TABLE_NAME + " WHERE "
            + SyncClockOutTableConstant.FINGER_PRINT_COLUMN_NAME + " =:fingerPrint AND " + SyncClockOutTableConstant.DATE_COLUMN_NAME + " =:date")
    SyncClockOut getSyncClockOutByFingerPrintAndDate(byte[] fingerPrint, String date);

    @Query("SELECT * FROM " + SyncClockOutTableConstant.TABLE_NAME + " WHERE " + SyncClockOutTableConstant.DATE_COLUMN_NAME + " =:date")
    List<SyncClockOut> getSyncClockOutsByDateNotLiveData(String date);
}
