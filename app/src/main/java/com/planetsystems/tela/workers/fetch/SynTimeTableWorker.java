package com.planetsystems.tela.workers.fetch;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

public class SynTimeTableWorker extends Worker {
    private SyncTimeTableDao syncTimeTableDao;

    public SynTimeTableWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncTimeTableDao = telaRoomDatabase.getSyncTimeTableDao();
    }

    @NonNull
    @Override
    public Result doWork() {
        return null;
    }
}
