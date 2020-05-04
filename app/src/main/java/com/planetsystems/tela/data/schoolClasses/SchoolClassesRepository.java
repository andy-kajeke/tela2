package com.planetsystems.tela.data.schoolClasses;

import com.planetsystems.tela.data.Teacher.TeacherRepository;
import com.planetsystems.tela.data.TelaRoomDatabase;

public class SchoolClassesRepository {

    private SyncSchoolClassesDao syncSchoolClassesDao;
    private static volatile SchoolClassesRepository INSTANCE;

    private SchoolClassesRepository(TelaRoomDatabase telaRoomDatabase) {
        syncSchoolClassesDao = telaRoomDatabase.getSyncSchoolClassesDao();
    }

    public static SchoolClassesRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (SchoolClassesRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new SchoolClassesRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void insertAllSchoolClasses(final SyncSchoolClasses syncSchoolClasses) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncSchoolClassesDao.insertSchoolClasses(syncSchoolClasses);
            }
        });
    }
}
