package com.planetsystems.tela.data.smc;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SyncSMCDao {
    @Insert
    void inertSyncTime(SyncSMC syncSMC);

    @Query("SELECT * FROM " + SyncSMCConstant.TABLE_NAME)
    LiveData<List<SyncSMC>> getSyncTimeBables();
}
