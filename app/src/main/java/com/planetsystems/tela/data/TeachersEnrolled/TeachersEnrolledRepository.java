package com.planetsystems.tela.data.TeachersEnrolled;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TeachersEnrolledRepository {
    private TeachersEnrolledDao teachersEnrolledDao;
    private static volatile TeachersEnrolledRepository INSTANCE;

    private TeachersEnrolledRepository(TelaRoomDatabase telaRoomDatabase) {
        teachersEnrolledDao = telaRoomDatabase.getTeachersEnrolledDao();
    }

    public static TeachersEnrolledRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (TeachersEnrolledRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new TeachersEnrolledRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void insertTeacherEnrolled(final TeachersEnrolled teachersEnrolled) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                teachersEnrolledDao.enrolledTeacher(teachersEnrolled);
            }
        });
    }

    public LiveData<List<TeachersEnrolled>> getTeachersEnrolled() {
        return teachersEnrolledDao.getAllTeachers();
    }
}
