package com.planetsystems.tela.data.Teacher;

import com.planetsystems.tela.data.TelaRoomDatabase;

public class TeacherRepository {
    private SyncTeacherDao syncTeacherDao;
    private static volatile TeacherRepository INSTANCE;

    private TeacherRepository(TelaRoomDatabase telaRoomDatabase) {
        syncTeacherDao = telaRoomDatabase.getSyncTeachersDao();
    }

    public static TeacherRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (TeacherRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new TeacherRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

}
