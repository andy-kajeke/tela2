package com.planetsystems.tela.data.attendance;

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
public interface SyncAttendanceRecordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNewLearnerAttendanceRecord(SyncAttendanceRecord syncAttendanceRecord);

    @Update
    void update(SyncAttendanceRecord syncAttendanceRecord);

    @Delete
    void delete(SyncAttendanceRecord syncAttendanceRecord);

    @Query("SELECT * FROM " + SyncTableConstants.SyncAttendanceRecords)
    LiveData<List<SyncAttendanceRecord>> getAllRecords();

    @Query("SELECT * FROM "
            + SyncTableConstants.SyncAttendanceRecords
            + " WHERE "
            + SyncAttendanceRecordConstant.SUBMISSION_DATE
            + " =:date"
            + " AND "
            + SyncAttendanceRecordConstant.DEPLOYMENT_UNIT
            + " =:classUnit"
    )
    LiveData<List<SyncAttendanceRecord>> getAttendanceByClassAndDate(String classUnit, String date);

    @Query("SELECT * FROM " + SyncTableConstants.SyncAttendanceRecords + " WHERE " + SyncAttendanceRecordConstant.IS_UPLOADED_COLUMN_NAME + " =:isUploaded")
    List<SyncAttendanceRecord> getLearnerRecords(boolean isUploaded);
}
