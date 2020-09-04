package com.planetsystems.tela.data.schoolClasses;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherTableConstants;

import java.util.List;

@Dao
public interface SyncSchoolClassesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSchoolClasses(SyncSchoolClasses syncSchoolClasses);

    @Query("SELECT * FROM " + SyncSchoolClassesConstants.TABLE_NAME + " ORDER BY " + SyncSchoolClassesConstants.COLUMN_CLASS_NAME)
    LiveData<List<SyncSchoolClasses>> getAllSchoolClasses();

    @Query(
            "SELECT * FROM " +
                    SyncSchoolClassesConstants.TABLE_NAME +
                    " WHERE " +
                    SyncSchoolClassesConstants.COLUMN_CLASS_NAME +
                    " =:className AND " +
                    SyncSchoolClassesConstants.COLUMN_CODE +
                    " =:code AND " +
                    SyncSchoolClassesConstants.COLUMN_ID +
                    " =:id "
    )
    SyncSchoolClasses getSyncSchool(
            String className,
            String code,
            String id
    );

}
