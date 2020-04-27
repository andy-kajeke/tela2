package com.planetsystems.tela.workers;

import android.content.Context;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.planetsystems.tela.workers.fetch.SyncTimeTableWorker;

public class WorkManagerTrigger {
    public static  void startFetchWorkers(Context context) {
        startFetchSyncTimeTableWorker(context);
    }

    public static void startFetchSyncTimeTableWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SyncTimeTableWorker.class)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);
    }
}
