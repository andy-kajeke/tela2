package com.planetsystems.tela.data.TeachersEnrolled;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;

import java.util.List;

@Dao
public interface TeachersEnrolledDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void enrolledTeacher(TeachersEnrolled teachersEnrolled);

    @Update
    void update(TeachersEnrolled teachersEnrolled);

    @Delete
    void deleteStaff(TeachersEnrolled teachersEnrolled);

    @Query("SELECT * FROM " + TeachersEnrolledConstants.TABLE_NAME)
    LiveData<List<TeachersEnrolled>> getAllTeachers();

    @Query("SELECT * FROM " + TeachersEnrolledConstants.TABLE_NAME + " WHERE " + TeachersEnrolledConstants.COLUMN_IS_UPLOADED + " =:isUploaded")
    List<TeachersEnrolled> getTeachersRecords(boolean isUploaded);
}
