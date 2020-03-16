package com.planetsystems.tela.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;
import com.planetsystems.tela.enties.SyncTeacher;

import java.util.List;


@Dao
public interface SyncTeachersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNewStaff(SyncTeacher syncTeacher);

    @Update
    void updateStaff(SyncTeacher syncTeacher);

    @Delete
    void deleteStaff(SyncTeacher syncTeacher);

    @Query("SELECT * FROM " + SyncTableConstants.SyncTeachers)
    LiveData<List<SyncTeacher>> getAllTeachers();
}
