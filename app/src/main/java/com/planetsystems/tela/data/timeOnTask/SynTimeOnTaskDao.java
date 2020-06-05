package com.planetsystems.tela.data.timeOnTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SynTimeOnTaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSynTimeOnTask(SynTimeOnTask synTimeOnTask);

    @Update
    void update(SynTimeOnTask synTimeOnTask);

    @Query("UPDATE " + SynTimeOnTaskConstant.TABLE_NAME
            + " SET "
            + SynTimeOnTaskConstant.SUPERVISION_STATUS + " =:supervisor_status "
            + " , "
            + SynTimeOnTaskConstant.IN_TIME + " =:inTime "
            + " , "
            + SynTimeOnTaskConstant.COMMENT + " =:comment "
            + " , "
            + SynTimeOnTaskConstant.SUPERVISION_ID + " =:supervisorId "
            +" WHERE "
            + SynTimeOnTaskConstant.ID + " =:primaryKey")
    void update(String supervisor_status, String inTime, String comment, String supervisorId, int primaryKey);

    @Query("SELECT * FROM " + SynTimeOnTaskConstant.TABLE_NAME)
    LiveData<List<SynTimeOnTask>> getSynTimeOnTasks();

    @Query(
            "SELECT * FROM " + SynTimeOnTaskConstant.TABLE_NAME
            + " WHERE "
            + SynTimeOnTaskConstant.EMPLOYEE_NUMBER
            + " =:employeeNumber AND "
            + SynTimeOnTaskConstant.TRANSACTION_DATE
            + " =:transactionDate AND "
            + SynTimeOnTaskConstant.TASK_ID
            + " =:taskId"
    )
    SynTimeOnTask getSynTimeOnTaskWithEmployeeNumberAndDate(String employeeNumber, String transactionDate, String taskId);

    @Query(
            "SELECT * FROM " + SynTimeOnTaskConstant.TABLE_NAME
                    + " WHERE "
                    + SynTimeOnTaskConstant.EMPLOYEE_NUMBER
                    + " =:employeeNumber AND "
                    + SynTimeOnTaskConstant.TRANSACTION_DATE
                    + " =:transactionDate AND "
                    + SynTimeOnTaskConstant.ACTION_STATUS
                    + " =:actionStatus"
    )
    LiveData<List<SynTimeOnTask>> getSynTimeOnTaskWithEmployeeNumberDateAndActionStatus(String employeeNumber, String transactionDate, String actionStatus);

    @Query("SELECT * FROM " + SynTimeOnTaskConstant.TABLE_NAME +
            " WHERE "
            + SynTimeOnTaskConstant.IS_UPLOADED_COLUMN_NAME_TEACHER +
            " =:isUploaded")
    List<SynTimeOnTask> getTeacherRecords(boolean isUploaded);

    @Query("SELECT * FROM " + SynTimeOnTaskConstant.TABLE_NAME +
            " WHERE "
            + SynTimeOnTaskConstant.IS_UPLOADED_COLUMN_NAME_SUPERVISOR +
            " =:isUploaded")
    List<SynTimeOnTask> getSupervisorRecords(boolean isUploaded);
}
