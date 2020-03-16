package com.planetsystems.tela.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.planetsystems.tela.enties.SyncTeachers;


@Dao
public interface SyncTeachersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNewStaff(SyncTeachers syncTeachers);

    @Update
    void updateStaff(SyncTeachers syncTeachers);

    @Delete
    void deleteStaff(SyncTeachers syncTeachers);
}
