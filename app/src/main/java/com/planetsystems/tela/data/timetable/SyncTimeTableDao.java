package com.planetsystems.tela.data.timetable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.planetsystems.tela.data.Teacher.SyncTeacherTableConstants;

import java.util.List;

@Dao
public interface SyncTimeTableDao {
    @Insert
    void insertSyncTimeTable(SyncTimeTable syncTimeTable);

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

    @Query("SELECT * FROM " + SyncTimeTableConstant.TABLE_NAME)
    List<SyncTimeTable> getSyncTimeTableDebug();

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
