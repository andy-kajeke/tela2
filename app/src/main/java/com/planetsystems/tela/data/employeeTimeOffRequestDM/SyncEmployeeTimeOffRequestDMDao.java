package com.planetsystems.tela.data.employeeTimeOffRequestDM;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;

import java.util.List;

@Dao
public interface SyncEmployeeTimeOffRequestDMDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNew(SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM);

    @Update
    void update(SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM);

    @Delete
    void delete(SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM);

    @Query("SELECT * FROM " + SyncTableConstants.SyncEmployeeTimeOffRequestDMs)
    LiveData<List<SyncEmployeeTimeOffRequestDM>> getAllRecords();
}
