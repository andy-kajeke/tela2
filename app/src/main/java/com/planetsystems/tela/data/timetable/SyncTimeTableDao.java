package com.planetsystems.tela.data.timetable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.data.Teacher.SyncTeacherTableConstants;
import com.planetsystems.tela.data.smc.SyncSMC;
import com.planetsystems.tela.data.smc.SyncSMCConstant;

import java.util.List;

@Dao
public interface SyncTimeTableDao {
    @Insert
    void insertSyncTimeTable(SyncTimeTable syncTimeTable);

    @Update
    void update(SyncTimeTable syncTimeTable);
    //update timetable by task id
    @Query("UPDATE " + SyncTimeTableConstant.TABLE_NAME
            + " SET "
            + SyncTimeTableConstant.START_TIME + " =:startTime "
            + " , "
            + SyncTimeTableConstant.END_TIME + " =:endTime "
            + " , "
            + SyncTimeTableConstant.EMPLOYEE_NO + " =:employeeNo "
            + " , "
            + SyncTimeTableConstant.EMPLOYEE_NAME + " =:employeeName "
            + " , "
            + SyncTimeTableConstant.IS_UPDATED + " =:is_updated "
            +" WHERE "
            + SyncTimeTableConstant.PRIMARY_KEY_COLUMN_NAME + " =:primaryKey")
    void update(String startTime, String endTime, String employeeNo, String employeeName, boolean is_updated, int primaryKey);

    //fetch all records from table
    @Query("SELECT * FROM " + SyncTimeTableConstant.TABLE_NAME)
    LiveData<List<SyncTimeTable>> getSyncTimeTables();

    @Query("SELECT * FROM "
            + SyncTimeTableConstant.TABLE_NAME
            + " WHERE " +
            SyncTimeTableConstant.EMPLOYEE_NO
            + " =:employeeID AND "
            + SyncTimeTableConstant.DAY
            + " =:day")
    LiveData<List<SyncTimeTable>> getSyncTimeTableByEmployeeIDForDay(String employeeID, String day);

    @Query("SELECT * FROM " + SyncTimeTableConstant.TABLE_NAME
            + " WHERE " + SyncTimeTableConstant.DAY +
            " =:day AND " + SyncTimeTableConstant.CLASS_UNIT + " =:classUnit"
    )
    LiveData<List<SyncTimeTable>> getSyncTimeTable(String day, String classUnit);

    @Query("SELECT * FROM " + SyncTimeTableConstant.TABLE_NAME +
            " WHERE "
            + SyncTimeTableConstant.IS_UPDATED +
            " =:isUpdated")
    List<SyncTimeTable> getTimeTableUpdate(boolean isUpdated);

    @Query(
          "SELECT * FROM " + SyncTimeTableConstant.TABLE_NAME +
          " WHERE " + SyncTimeTableConstant.END_TIME +
          " =:endTime AND " + SyncTimeTableConstant.START_TIME +
          " =:startTime AND " + SyncTimeTableConstant.DAY +
          " =:day AND " + SyncTimeTableConstant.EMPLOYEE_ID +
          " =:employeeId AND " + SyncTimeTableConstant.EMPLOYEE_NO + " =:employeeNo"
    )
    SyncTimeTable getUnitTimeTable(
            String endTime,
            String startTime,
            String day,
            String employeeId,
            String employeeNo
    );

}
