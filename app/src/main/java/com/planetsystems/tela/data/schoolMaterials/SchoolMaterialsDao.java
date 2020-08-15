package com.planetsystems.tela.data.schoolMaterials;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClassesConstants;

import java.util.List;

@Dao
public interface SchoolMaterialsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSchoolMaterials(SchoolMaterials schoolMaterials);

    @Query("SELECT * FROM " + SchoolMaterialsConstants.TABLE_NAME)
    LiveData<List<SchoolMaterials>> getAllSchoolMaterials();

    @Query(
            "SELECT * FROM " +
                    SchoolMaterialsConstants.TABLE_NAME +
                    " WHERE " +
                    SchoolMaterialsConstants.COLUMN_ITEM_NAME +
                    " =:itemName AND " +
                    SchoolMaterialsConstants.COLUMN_CODE +
                    " =:code AND " +
                    SchoolMaterialsConstants.COLUMN_ID +
                    " =:id "
    )
    SchoolMaterials getSchoolMaterials(
            String itemName,
            String code,
            String id
    );
}
