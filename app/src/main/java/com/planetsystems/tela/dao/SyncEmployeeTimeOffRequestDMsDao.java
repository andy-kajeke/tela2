package com.planetsystems.tela.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;
import com.planetsystems.tela.enties.SyncEmployeeTimeOffRequestDMs;

import java.util.List;

@Dao
public interface SyncEmployeeTimeOffRequestDMsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNew(SyncEmployeeTimeOffRequestDMs syncEmployeeTimeOffRequestDMs);

    @Update
    void update(SyncEmployeeTimeOffRequestDMs syncEmployeeTimeOffRequestDMs);

    @Delete
    void delete(SyncEmployeeTimeOffRequestDMs syncEmployeeTimeOffRequestDMs);

    @Query("SELECT * FROM " + SyncTableConstants.SyncEmployeeTimeOffRequestDMs)
    LiveData<List<SyncEmployeeTimeOffRequestDMs>> getAllRecords();
}
