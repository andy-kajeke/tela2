package com.planetsystems.tela.data.Teacher;

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
public interface SyncTeacherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void enrollTeacher(SyncTeacher syncTeacher);

    @Update
    void updateStaff(SyncTeacher syncTeacher);

    @Delete
    void deleteStaff(SyncTeacher syncTeacher);

    @Query("SELECT * FROM " + SyncTeacherTableConstants.TABLE_NAME)
    LiveData<List<SyncTeacher>> getAllTeachers();

    @Query("SELECT * FROM " + SyncTeacherTableConstants.TABLE_NAME + " WHERE " + SyncTeacherTableConstants.EMPLOYEE_NUMBER_COLUMN_NAME + " =:employeeNumber")
    SyncTeacher getSyncTeacherWithEmployeeNumber(String employeeNumber);

    @Query(
            "SELECT * FROM " +
            SyncTeacherTableConstants.TABLE_NAME +
            " WHERE " +
            SyncTeacherTableConstants.EMPLOYEE_NUMBER_COLUMN_NAME +
            " =:employeeNumber AND " +
            SyncTeacherTableConstants.ID_COLUMN_NAME +
            " =:id AND " +
            SyncTeacherTableConstants.NATIONAL_ID_COLUMN_NAME +
            " =:nationalID "
    )
    SyncTeacher getSyncTeacher(
            String employeeNumber,
            String id,
            String nationalID
    );
}
