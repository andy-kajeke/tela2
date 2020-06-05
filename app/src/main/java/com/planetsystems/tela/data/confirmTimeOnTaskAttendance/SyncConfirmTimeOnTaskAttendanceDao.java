package com.planetsystems.tela.data.confirmTimeOnTaskAttendance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SyncConfirmTimeOnTaskAttendanceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNewConfirmation(SyncConfirmTimeOnTaskAttendance syncConfirmTimeOnTaskAttendance);

    @Update
    void update(SyncConfirmTimeOnTaskAttendance syncConfirmTimeOnTaskAttendance);

    @Delete
    void delete(SyncConfirmTimeOnTaskAttendance syncConfirmTimeOnTaskAttendance);

    @Query("SELECT * FROM " + SyncConfirmTimeOnTaskAttendanceConstants.TABLE_NAME)
    LiveData<List<SyncConfirmTimeOnTaskAttendance>> getAllRecords();

    @Query("SELECT * FROM " + SyncConfirmTimeOnTaskAttendanceConstants.TABLE_NAME +
            " WHERE "
            + SyncConfirmTimeOnTaskAttendanceConstants.IS_UPLOADED +
            " =:isUploaded")
    List<SyncConfirmTimeOnTaskAttendance> getSupervisorRecords(boolean isUploaded);
}
