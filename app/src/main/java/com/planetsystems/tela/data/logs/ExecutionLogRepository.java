package com.planetsystems.tela.data.logs;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.ClockIn.ClockInRepository;
import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class ExecutionLogRepository {
    private static ExecutionLogRepository INSTANCE;
    private ExecutionLogDao executionLogDao;

    private ExecutionLogRepository (TelaRoomDatabase telaRoomDatabase) {
        executionLogDao = telaRoomDatabase.getExecutionLogDao();
    }

    public static ExecutionLogRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null) {
            synchronized (ClockInRepository.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = new ExecutionLogRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<List<ExecutionLog>> getLogs() throws ExecutionException, InterruptedException {
        Callable<LiveData<List<ExecutionLog>>> callable = new Callable<LiveData<List<ExecutionLog>>>() {
            @Override
            public LiveData<List<ExecutionLog>> call() throws Exception {
                return executionLogDao.getLogs();
            }
        };
        return TelaRoomDatabase.db_executor.submit(callable).get();
    }

    public void logMessage(final ExecutionLog executionLog){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                executionLogDao.logMessage(executionLog);
            }
        });
    }

    public void clearLogs() {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                executionLogDao.clearLogs();
            }
        });
    }
}
