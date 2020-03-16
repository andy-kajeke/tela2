package com.planetsystems.tela.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;
import com.planetsystems.tela.enties.SyncTeachers;

import java.util.List;


@Dao
public interface SyncTeachersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNewStaff(SyncTeachers syncTeachers);

    @Update
    void updateStaff(SyncTeachers syncTeachers);

    @Delete
    void deleteStaff(SyncTeachers syncTeachers);

    @Query("SELECT * FROM " + SyncTableConstants.SyncTeachers)
    LiveData<List<SyncTeachers>> getAllTeachers();
}
