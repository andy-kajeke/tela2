package com.planetsystems.tela.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.List;

public class SyncClockInTeacherUploadWorker extends Worker {
    SyncClockInDao syncClockInDao;
    /*
    * This worker will run periodically usually 3 times a day to upload
    * Clock ins for a give school,
    * It queries all clock in data and pushes them to the backend
    * */
    public SyncClockInTeacherUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncClockInDao = telaRoomDatabase.getSyncClockInDao();
    }

    @NonNull
    @Override
    public Result doWork() {
        List<SyncClockIn> syncClockIns = syncClockInDao.getSyncClockInsForBackUp();
        for(SyncClockIn syncClockIn: syncClockIns) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncClockIn.toString());
        }
        return null;
    }
}
