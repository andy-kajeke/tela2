package com.planetsystems.tela.data.Teacher;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    public SyncTeacher getTeacherWithEmployeeNumber(final String employeeNumber) throws ExecutionException, InterruptedException {
        Callable<SyncTeacher> callable = new Callable<SyncTeacher>() {
            @Override
            public SyncTeacher call() throws Exception {
                return syncTeacherDao.getSyncTeacherWithEmployeeNumber(employeeNumber);
            }
        };

        Future<SyncTeacher> future = TelaRoomDatabase.db_executor.submit(callable);
        return  future.get();
    }
}
