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

    @Query("SELECT * FROM " + SyncTableConstants.SyncTeachers)
    LiveData<List<SyncTeacher>> getAllTeachers();

    @Query("SELECT * FROM " + SyncTableConstants.SyncTeachers + " WHERE ")
    SyncTeacher getSyncTeacherWithEmployeeNumber(String employeeNumber);
}
