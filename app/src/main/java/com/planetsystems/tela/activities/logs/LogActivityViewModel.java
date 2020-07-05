package com.planetsystems.tela.activities.logs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.logs.ExecutionLog;
import com.planetsystems.tela.logs.ExecutionLogRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LogActivityViewModel extends AndroidViewModel {
    private ExecutionLogRepository executionLogRepository;
    public LogActivityViewModel(@NonNull Application application) {
        super(application);
        executionLogRepository = MainRepository.getInstance(application).getExecutionLogRepository();
    }

    public LiveData<List<ExecutionLog>> getLogs() {
        try {
            return executionLogRepository.getLogs();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
