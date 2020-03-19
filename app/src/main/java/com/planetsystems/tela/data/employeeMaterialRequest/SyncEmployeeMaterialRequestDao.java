package com.planetsystems.tela.data.employeeMaterialRequest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SyncEmployeeMaterialRequestDao {
    @Insert
    void insertSyncEmployeeMaterialRequest(SyncEmployeeMaterialRequest materialRequest);

    @Query("SELECT * FROM " + SyncEmployeeMaterialRequestConstant.TABLE_NAME)
    LiveData<List<SyncEmployeeMaterialRequest>> getAllSyncEmployeeMaterialRequest();

}
