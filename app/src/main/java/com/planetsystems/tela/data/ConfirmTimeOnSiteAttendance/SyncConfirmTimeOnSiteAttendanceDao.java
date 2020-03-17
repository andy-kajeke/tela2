package com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SyncConfirmTimeOnSiteAttendanceDao {
    @Insert
    void insertSyncConfirmTimeOnSiteAttendance(SyncConfirmTimeOnSiteAttendance onSiteAttendance);

    @Query("SELECT * FROM " + SyncConfirmTimeOnSiteAttendanceConstant.TABLE_NAME)
    LiveData<List<SyncConfirmTimeOnSiteAttendance>> getSyncConfirmTimeOnSiteAttendance();
}
