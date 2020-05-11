package com.planetsystems.tela.data.timeOnTask;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TimeOnTaskRepository {
    private SynTimeOnTaskDao synTimeOnTaskDao;
    private static volatile TimeOnTaskRepository INSTANCE;

    private TimeOnTaskRepository(TelaRoomDatabase telaRoomDatabase) {
        synTimeOnTaskDao = telaRoomDatabase.getSyncTimeOnTaskDao();
    }

    public static TimeOnTaskRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (TimeOnTaskRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new TimeOnTaskRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void insertSyncTimeOneTask(final SynTimeOnTask synTimeOnTask) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                synTimeOnTaskDao.inertSynTimeOnTask(synTimeOnTask);
            }
        });
    }

    public LiveData<List<SynTimeOnTask>> getAllTimeOnTask() {
        return synTimeOnTaskDao.getSynTimeOnTasks();
    }

//    public SyncTeacher getTeacherWithEmployeeNumber(final String employeeNumber) throws ExecutionException, InterruptedException {
//        Callable<SyncTeacher> callable = new Callable<SyncTeacher>() {
//            @Override
//            public SyncTeacher call() throws Exception {
//                return syncTeacherDao.getSyncTeacherWithEmployeeNumber(employeeNumber);
//            }
//        };
//
//        Future<SyncTeacher> future = TelaRoomDatabase.db_executor.submit(callable);
//        return  future.get();
//    }

//    public SyncTeacher getTeacherFingerPrint(final String fingerPrint) throws ExecutionException, InterruptedException {
//        Callable<SyncTeacher> callable = new Callable<SyncTeacher>() {
//            @Override
//            public SyncTeacher call() throws Exception {
//                return syncTeacherDao.getSyncTeacherWithEmployeeNumber(fingerPrint);
//            }
//        };
//
//        Future<SyncTeacher> future = TelaRoomDatabase.db_executor.submit(callable);
//        return  future.get();
//    }
}
