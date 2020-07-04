package com.planetsystems.tela.data.Teacher;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM " + SyncTeacherTableConstants.TABLE_NAME)
    List<SyncTeacher> getList();

    @Query(
            "SELECT * FROM " + SyncTeacherTableConstants.TABLE_NAME + " WHERE " + SyncTeacherTableConstants.EMPLOYEE_NUMBER_COLUMN_NAME + " =:employeeNumber")
    SyncTeacher getSyncTeacherWithEmployeeNumber(String employeeNumber);

    @Query(
            "SELECT * FROM " +
                    SyncTeacherTableConstants.TABLE_NAME + " WHERE " + SyncTeacherTableConstants.FINGER_PRINT_COLUMN_NAME + " =:fingerPrint")
    SyncTeacher getSyncTeacherWithFingerPrint(byte[] fingerPrint);

    @Query(
            "SELECT * FROM " +
            SyncTeacherTableConstants.TABLE_NAME +
            " WHERE " +
            SyncTeacherTableConstants.NATIONAL_ID_COLUMN_NAME +
            " =:nationalID "
    )
    SyncTeacher getSyncTeacher(
            String nationalID
    );

    @Query("SELECT * FROM " +
            SyncTeacherTableConstants.TABLE_NAME +
            " WHERE " + SyncTeacherTableConstants.IS_STORED_LOCALLY + " =:isStoredLocally"
    )
    List<SyncTeacher> getListStoredLocally(boolean isStoredLocally);

    @Query("SELECT * FROM " + SyncTeacherTableConstants.TABLE_NAME + " WHERE " + SyncTeacherTableConstants.NATIONAL_ID_COLUMN_NAME + " =:nin")
    SyncTeacher findTeacherWithNationalID(String nin);
}
