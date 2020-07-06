package com.planetsystems.tela.data.logs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExecutionLogDao {
    @Insert
    void logMessage(ExecutionLog executionLog);

    @Query("SELECT * FROM " + ExecutionLogConstants.TABLE_NAME)
    LiveData<List<ExecutionLog>> getLogs();

    @Query("DELETE FROM " + ExecutionLogConstants.TABLE_NAME)
    void clearLogs();
}
