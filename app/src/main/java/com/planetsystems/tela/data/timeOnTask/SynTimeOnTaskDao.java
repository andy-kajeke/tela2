package com.planetsystems.tela.data.timeOnTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SynTimeOnTaskDao {
    @Insert(entity = SynTimeOnTask.class)
    void inertSynTimeOnTask(SynTimeOnTask synTimeOnTask);

    @Query("SELECT * FROM " + SynTimeOnTaskConstant.TABLE_NAME)
    LiveData<List<SynTimeOnTask>> getSynTimeOnTasks();
}
