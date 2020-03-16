package com.planetsystems.tela.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;
import com.planetsystems.tela.enties.SyncConfirmTimeOnTaskAttendances;

import java.util.List;

public interface SyncConfirmTimeOnTaskAttendancesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNew(SyncConfirmTimeOnTaskAttendances syncConfirmTimeOnTaskAttendances);

    @Update
    void update(SyncConfirmTimeOnTaskAttendances syncConfirmTimeOnTaskAttendances);

    @Delete
    void delete(SyncConfirmTimeOnTaskAttendances syncConfirmTimeOnTaskAttendances);

    @Query("SELECT * FROM " + SyncTableConstants.SyncConfirmTimeOnTaskAttendances)
    LiveData<List<SyncConfirmTimeOnTaskAttendances>> getAllRecords();
}
