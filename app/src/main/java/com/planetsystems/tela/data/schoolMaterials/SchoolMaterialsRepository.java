package com.planetsystems.tela.data.schoolMaterials;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClassesDao;

import java.util.List;

public class SchoolMaterialsRepository {

    private SchoolMaterialsDao schoolMaterialsDao;
    private static volatile SchoolMaterialsRepository INSTANCE;

    private SchoolMaterialsRepository(TelaRoomDatabase telaRoomDatabase) {
        schoolMaterialsDao = telaRoomDatabase.getSchoolMaterialsDao();
    }

    public static SchoolMaterialsRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (SchoolMaterialsRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new SchoolMaterialsRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void insertAllSchoolMaterials(final SchoolMaterials schoolMaterials) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                schoolMaterialsDao.insertSchoolMaterials(schoolMaterials);
            }
        });
    }

    public LiveData<List<SchoolMaterials>> getAllMaterials(){
        return schoolMaterialsDao.getAllSchoolMaterials();
    }
}
