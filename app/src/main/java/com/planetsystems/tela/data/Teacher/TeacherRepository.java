package com.planetsystems.tela.data.Teacher;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.logs.ExecutionLog;
import com.planetsystems.tela.data.logs.ExecutionLogRepository;
import com.planetsystems.tela.utils.DynamicData;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TeacherRepository {
    private SyncTeacherDao syncTeacherDao;
    private static volatile TeacherRepository INSTANCE;
    private ExecutionLogRepository executionLogRepository;
    private final String className = getClass().getSimpleName();

    private TeacherRepository(TelaRoomDatabase telaRoomDatabase) {
        executionLogRepository = ExecutionLogRepository.getInstance(telaRoomDatabase);
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
        String message = "Inserting Teacher: ";
        ExecutionLog executionLog = new ExecutionLog(
                message,
                DynamicData.getDate(),
                new Date().toString(), className, null, null,
                syncTeacher.toString(), null, null
        );
        executionLogRepository.logMessage(executionLog);
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                String message = "Enrolling a teacher of National ID: " + syncTeacher.getNationalId()
                        + " and fingerprint of size: " + syncTeacher.getFingerPrint().length;

                ExecutionLog executionLog = new ExecutionLog(message,
                        DynamicData.getDate(), new Date().toString(), className, null, null, syncTeacher.toString(), null, null);
                executionLogRepository.logMessage(executionLog);
                syncTeacherDao.enrollTeacher(syncTeacher);
            }
        });
    }

    public LiveData<List<SyncTeacher>> getAllTeachers() {
        return syncTeacherDao.getAllTeachers();
    }

    public List<SyncTeacher> getTeachers() {
        String message = "Getting all teachers: ";
        ExecutionLog executionLog = new ExecutionLog(
                message,
                DynamicData.getDate(),
                new Date().toString(), className, null, null,
                null, null, null
        );
        executionLogRepository.logMessage(executionLog);

        Callable<List<SyncTeacher>> callable = new Callable<List<SyncTeacher>>() {
            @Override
            public List<SyncTeacher> call() throws Exception {
                List<SyncTeacher> syncTeachers = syncTeacherDao.getList();
                for (SyncTeacher syncTeacher: syncTeachers) {
                    String message = "Sync Teacher of National ID: " + syncTeacher.getNationalId() +
                            " has fingerprint of size:  " + syncTeacher.getFingerPrint().length + " byte array of"
                            + String.valueOf(syncTeacher.getFingerPrint());
                    ExecutionLog executionLog = new ExecutionLog(
                            message,
                            DynamicData.getDate(),
                            new Date().toString(), className, null, null,
                            syncTeacher.toString(), null, null
                    );
                }
                return syncTeacherDao.getList();
            }
        };
        try {
            return TelaRoomDatabase.db_executor.submit(callable).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
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

    public SyncTeacher getTeacherEmployeeNumber(final String employeeNumber) throws ExecutionException, InterruptedException {
        Callable<SyncTeacher> callable = new Callable<SyncTeacher>() {
            @Override
            public SyncTeacher call() throws Exception {
                return syncTeacherDao.getSyncTeacherWithEmployeeNumber(employeeNumber);
            }
        };

        Future<SyncTeacher> future = TelaRoomDatabase.db_executor.submit(callable);
        return  future.get();
    }

    public SyncTeacher getTeacherWithFingerPrint(final byte[] fingerPrint) throws ExecutionException, InterruptedException {
        Callable<SyncTeacher> callable = new Callable<SyncTeacher>() {
            @Override
            public SyncTeacher call() throws Exception {
                return syncTeacherDao.getSyncTeacherWithFingerPrint(fingerPrint);
            }
        };
        Future<SyncTeacher> future = TelaRoomDatabase.db_executor.submit(callable);
        return future.get();
    }

    public SyncTeacher getTeacherWithNationalIDNumber(final String nin) throws ExecutionException, InterruptedException {
        Callable<SyncTeacher> callable = new Callable<SyncTeacher>() {
            @Override
            public SyncTeacher call() throws Exception {
                return syncTeacherDao.findTeacherWithNationalID(nin);
            }
        };

        Future<SyncTeacher> future = TelaRoomDatabase.db_executor.submit(callable);
        return  future.get();
    }

    public SyncTeacher updateTeacher(final SyncTeacher syncTeacher) throws ExecutionException, InterruptedException {
        Callable<SyncTeacher> callable = new Callable<SyncTeacher>() {
            @Override
            public SyncTeacher call() throws Exception {
                syncTeacherDao.updateStaff(syncTeacher);
                return syncTeacher;
            }
        };

        Future<SyncTeacher> future = TelaRoomDatabase.db_executor.submit(callable);
        return  future.get();
    }

    public SyncTeacher getTeacherWithNationalID(final String nationalID) throws ExecutionException, InterruptedException {
        Callable<SyncTeacher> callable = new Callable<SyncTeacher>() {
            @Override
            public SyncTeacher call() throws Exception {
                return syncTeacherDao.getSyncTeacher(nationalID);
            }
        };

        Future<SyncTeacher> future = TelaRoomDatabase.db_executor.submit(callable);
        return  future.get();
    }
}
