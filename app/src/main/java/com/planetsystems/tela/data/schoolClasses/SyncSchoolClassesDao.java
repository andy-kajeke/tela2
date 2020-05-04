package com.planetsystems.tela.data.schoolClasses;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

public interface SyncSchoolClassesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSchoolClasses(SyncSchoolClasses syncSchoolClasses);

    @Query("SELECT * FROM " + SyncSchoolClassesConstants.TABLE_NAME)
    LiveData<List<SyncSchoolClasses>> getAllSchoolClasses();

}
