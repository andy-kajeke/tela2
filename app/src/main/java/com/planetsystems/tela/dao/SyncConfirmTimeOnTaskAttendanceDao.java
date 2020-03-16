package com.planetsystems.tela.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;
import com.planetsystems.tela.enties.SyncConfirmTimeOnTaskAttendance;

import java.util.List;

public interface SyncConfirmTimeOnTaskAttendanceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNew(SyncConfirmTimeOnTaskAttendance syncConfirmTimeOnTaskAttendance);

    @Update
    void update(SyncConfirmTimeOnTaskAttendance syncConfirmTimeOnTaskAttendance);

    @Delete
    void delete(SyncConfirmTimeOnTaskAttendance syncConfirmTimeOnTaskAttendance);

    @Query("SELECT * FROM " + SyncTableConstants.SyncConfirmTimeOnTaskAttendances)
    LiveData<List<SyncConfirmTimeOnTaskAttendance>> getAllRecords();
}
