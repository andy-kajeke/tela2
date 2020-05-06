package com.planetsystems.tela.workers.upload;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.TelaRoomDatabase;

public class SyncClockInTeacherUploadWorker extends Worker {
    SyncClockInDao syncClockInDao;


    public SyncClockInTeacherUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        syncClockInDao = TelaRoomDatabase.getInstance(getApplicationContext()).getSyncClockInDao();
    }

    @NonNull
    @Override
    public Result doWork() {
        return null;
    }
}
