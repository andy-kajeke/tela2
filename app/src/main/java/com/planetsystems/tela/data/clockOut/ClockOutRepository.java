package com.planetsystems.tela.data.clockOut;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ClockOutRepository {
    private static volatile ClockOutRepository INSTANCE;
    private SyncClockOutDao syncClockOutDao;

    private ClockOutRepository(TelaRoomDatabase telaRoomDatabase) {
        syncClockOutDao = telaRoomDatabase.getSyncClockOutDao();
    }

    public static ClockOutRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (ClockOutRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClockOutRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void insertSynClockOut(final SyncClockOut syncClockOut) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncClockOutDao.insertClockOutTeacher(syncClockOut);
            }
        });
    }

    public SyncClockOut getSyncClockOutByEmployeeNumberAndDate(final String employeeNumber, final String date) throws ExecutionException, InterruptedException {
        Callable<SyncClockOut> callable = new Callable<SyncClockOut>() {
            @Override
            public SyncClockOut call() throws Exception {
                return syncClockOutDao.getSyncClockOutByEmployeeIdAndDate(employeeNumber, date);
            }
        };
        Future<SyncClockOut> future = TelaRoomDatabase.db_executor.submit(callable);
        return  future.get();
    }

    public LiveData<List<SyncClockOut>> allClockOuts(){
        return syncClockOutDao.getAllClockOuts();
    }

    public SyncClockOut getSyncClockOutByFingerPrintAndDate(final byte[] fingerPrint, final String date) throws ExecutionException, InterruptedException {
        Callable<SyncClockOut> callable = new Callable<SyncClockOut>() {
            @Override
            public SyncClockOut call() throws Exception {
                return syncClockOutDao.getSyncClockOutByFingerPrintAndDate(fingerPrint, date);
            }
        };
        return TelaRoomDatabase.db_executor.submit(callable).get();
    }
}
