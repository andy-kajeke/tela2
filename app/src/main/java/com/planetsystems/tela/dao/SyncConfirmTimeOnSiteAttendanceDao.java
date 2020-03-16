package com.planetsystems.tela.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.planetsystems.tela.constants.SyncConfirmTimeOnSiteAttendanceName;
import com.planetsystems.tela.enties.SyncConfirmTimeOnSiteAttendance;

import java.util.List;

@Dao
public interface SyncConfirmTimeOnSiteAttendanceDao {
    @Insert
    void insertSyncConfirmTimeOnSiteAttendance(SyncConfirmTimeOnSiteAttendance onSiteAttendance);

    @Query("SELECT * FROM " + SyncConfirmTimeOnSiteAttendanceName.TABLE_NAME)
    LiveData<List<SyncConfirmTimeOnSiteAttendance>> getSyncConfirmTimeOnSiteAttendance();
}
