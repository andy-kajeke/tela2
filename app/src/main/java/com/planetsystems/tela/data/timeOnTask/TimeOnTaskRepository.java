package com.planetsystems.tela.data.timeOnTask;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TimeOnTaskRepository {
    private SynTimeOnTaskDao synTimeOnTaskDao;
    private static volatile TimeOnTaskRepository INSTANCE;
    private SyncTimeTableDao syncTimeTableDao;

    private TimeOnTaskRepository(TelaRoomDatabase telaRoomDatabase) {
        synTimeOnTaskDao = telaRoomDatabase.getSyncTimeOnTaskDao();
        syncTimeTableDao = telaRoomDatabase.getSyncTimeTableDao();
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
                synTimeOnTaskDao.insertSynTimeOnTask(synTimeOnTask);
            }
        });
    }

    public void supervisorConfirmationSyncTimeOneTask(final String supervisor_status, final String inTime, final String comment, final String supervisorId, final int primaryKey) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                synTimeOnTaskDao.update(supervisor_status, inTime, comment, supervisorId, primaryKey);
            }
        });
    }

    public LiveData<List<SynTimeOnTask>> getAllTimeOnTask() {
        return synTimeOnTaskDao.getSynTimeOnTasks();
    }

    public LiveData<List<SyncTimeTable>> getSyncTimeTableByEmployeeIDForDay(String employeeNumber, String dayOfTheWeek) {
        return syncTimeTableDao.getSyncTimeTableByEmployeeIDForDay(employeeNumber, dayOfTheWeek);
    }

    public LiveData<List<SyncTimeTable>> getSyncTimeTableByEmployeeIDForClassUnit(String day, String classUnit) {
        return syncTimeTableDao.getSyncTimeTable(day, classUnit);
    }

    public SynTimeOnTask getSynTimeOnTaskWithEmployeeNumberAndDate(final String employeeNumber, final String transactionDate, final String taskId) throws ExecutionException, InterruptedException {
        Callable<SynTimeOnTask> callable = new Callable<SynTimeOnTask>() {
            @Override
            public SynTimeOnTask call() throws Exception {
                return synTimeOnTaskDao.getSynTimeOnTaskWithEmployeeNumberAndDate(employeeNumber, transactionDate, taskId);
            }
        };
        Future<SynTimeOnTask> synTimeOnTaskFuture = TelaRoomDatabase.db_executor.submit(callable);
        return synTimeOnTaskFuture.get();
    }

    public LiveData<List<SynTimeOnTask>> getTaskWiThActionStatusPresent(final String employeeNumber, final String transactionDate, final String actionStatus){
        return synTimeOnTaskDao.getSynTimeOnTaskWithEmployeeNumberDateAndActionStatus(employeeNumber, transactionDate, actionStatus);
    }
}
