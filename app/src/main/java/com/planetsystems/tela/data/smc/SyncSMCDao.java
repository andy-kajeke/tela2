package com.planetsystems.tela.data.smc;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SyncSMCDao {
    @Insert
    void inertSmcObservations(SyncSMC syncSMC);

    @Update
    void update(SyncSMC syncSMC);

    @Query("SELECT * FROM " + SyncSMCConstant.TABLE_NAME)
    LiveData<List<SyncSMC>> getSmcRecords();

    @Query("SELECT * FROM " + SyncSMCConstant.TABLE_NAME +
            " WHERE "
            + SyncSMCConstant.IS_UPLOADED +
            " =:isUploaded")
    List<SyncSMC> getUnSyncedRecords(boolean isUploaded);
}
