package com.planetsystems.tela.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.planetsystems.tela.constants.SyncEmployeeMaterialRequestName;
import com.planetsystems.tela.enties.SyncEmployeeMaterialRequest;

import java.util.List;

@Dao
public interface SyncEmployeeMaterialRequestDao {
    @Insert
    void insertSyncEmployeeMaterialRequest(SyncEmployeeMaterialRequest materialRequest);

    @Query("SELECT * FROM " + SyncEmployeeMaterialRequestName.TABLE_NAME)
    LiveData<List<SyncEmployeeMaterialRequest>> getAllSyncEmployeeMaterialRequest();

}
