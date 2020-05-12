package com.planetsystems.tela.data.timeOnTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SynTimeOnTaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSynTimeOnTask(SynTimeOnTask synTimeOnTask);

    @Query("SELECT * FROM " + SynTimeOnTaskConstant.TABLE_NAME)
    LiveData<List<SynTimeOnTask>> getSynTimeOnTasks();

    @Query(
            "SELECT * FROM " + SynTimeOnTaskConstant.TABLE_NAME
            + " WHERE "
            + SynTimeOnTaskConstant.EMPLOYEE_NUMBER
            + " =:employeeNumber AND "
            + SynTimeOnTaskConstant.TRANSACTION_DATE
            + " =:transactionDate"
    )
    SynTimeOnTask getSynTimeOnTaskWithEmployeeNumberAndDate(String employeeNumber, String transactionDate);
}
