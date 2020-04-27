package com.planetsystems.tela.data.Teacher;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.List;

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

    public void insertSyncTeacher(final SyncTeacher syncTeacher) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncTeacherDao.enrollTeacher(syncTeacher);
            }
        });
    }

    public LiveData<List<SyncTeacher>> getAllTeachers() {
        return syncTeacherDao.getAllTeachers();
    }
}
