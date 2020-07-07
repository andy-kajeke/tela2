package com.planetsystems.tela.data.ClockIn;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ClockInRepository {
    private static ClockInRepository INSTANCE;
    private SyncClockInDao syncClockInDao;

    private ClockInRepository(TelaRoomDatabase telaRoomDatabase) {
        syncClockInDao = telaRoomDatabase.getSyncClockInDao();
    }

    public static ClockInRepository getInstance(final TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null) {
            synchronized (ClockInRepository.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = new ClockInRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void synClockInTeacher(final SyncClockIn clockIn){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncClockInDao.syncClockInTeacherWithID(clockIn);
            }
        });
    }

    public SyncClockIn getSyncClockInByEmployeeIDAndDate(final String employeeNumber, final String date) throws ExecutionException, InterruptedException {
        Callable<SyncClockIn> callable = new Callable<SyncClockIn>() {
            @Override
            public SyncClockIn call() throws Exception {
                return syncClockInDao.getSyncClockInByEmployeeIDAndDate(employeeNumber, date);
            }
        };
        Future<SyncClockIn> future = TelaRoomDatabase.db_executor.submit(callable);
        return future.get();
    }

    public LiveData<List<SyncClockIn>> getClockedInTeachersByDate (String dateOfTheDay) {
        return syncClockInDao.getSyncClockInByDate(dateOfTheDay);
    }

    public List<SyncClockIn> getClockedInTeachersByDateNoteLiveData (final String dateOfTheDay) throws ExecutionException, InterruptedException {
        Callable<List<SyncClockIn>> callable = new Callable<List<SyncClockIn>>() {
            @Override
            public List<SyncClockIn> call() throws Exception {
                return syncClockInDao.getSyncClockInByDateNotLiveData(dateOfTheDay);
            }
        };
        Future<List<SyncClockIn>> future = TelaRoomDatabase.db_executor.submit(callable);
        return future.get();
    }

    public LiveData<List<SyncClockIn>> getAllClockedIn () {
        return syncClockInDao.getAllClockIn();
    }


    public SyncClockIn getSyncClockInByFingerPrintAndDate(final byte[] fingerPrint, final String date) throws ExecutionException, InterruptedException {
        Callable<SyncClockIn> callable = new Callable<SyncClockIn>() {
            @Override
            public SyncClockIn call() throws Exception {
                return syncClockInDao.getSynClickWithFingerprintAndDate(fingerPrint, date);
            }
        };

        return TelaRoomDatabase.db_executor.submit(callable).get();
    }

    public List<SyncClockIn> getClockInByDate(final String date) throws ExecutionException, InterruptedException {
        Callable<List<SyncClockIn>> callable = new Callable<List<SyncClockIn>>() {
            @Override
            public List<SyncClockIn> call() throws Exception {
                return syncClockInDao.getSyncClockInByDateNotLiveData(date);
            }
        };

        return TelaRoomDatabase.db_executor.submit(callable).get();
    }
}
