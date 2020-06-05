package com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecordConstant;

import java.util.List;

@Dao
public interface SyncConfirmTimeOnSiteAttendanceDao {
    @Insert
    void insertSyncConfirmTimeOnSiteAttendance(SyncConfirmTimeOnSiteAttendance onSiteAttendance);

    @Update
    void update(SyncConfirmTimeOnSiteAttendance onSiteAttendance);

    @Query("SELECT * FROM " + SyncConfirmTimeOnSiteAttendanceConstant.TABLE_NAME)
    LiveData<List<SyncConfirmTimeOnSiteAttendance>> getSyncConfirmTimeOnSiteAttendance();

    @Query("SELECT * FROM " + SyncConfirmTimeOnSiteAttendanceConstant.TABLE_NAME +
            " WHERE "
            + SyncConfirmTimeOnSiteAttendanceConstant.IS_UPLOADED_COLUMN_NAME + " =:isUploaded")
    List<SyncConfirmTimeOnSiteAttendance> getConfirmationRecords(boolean isUploaded);
}
