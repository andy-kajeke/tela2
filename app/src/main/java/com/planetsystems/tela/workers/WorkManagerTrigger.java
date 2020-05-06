package com.planetsystems.tela.workers;

import android.content.Context;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.planetsystems.tela.workers.fetch.SyncSchoolClassesWorker;
import com.planetsystems.tela.workers.fetch.SyncTeacherWorker;
import com.planetsystems.tela.workers.fetch.SyncTimeTableWorker;
import com.planetsystems.tela.workers.upload.SyncClockInTeacherUploadWorker;
import com.planetsystems.tela.workers.upload.SyncClockOutTeacherUploadWorker;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class WorkManagerTrigger {
    public static  void startFetchWorkers(Context context) {
        startFetchSyncTimeTableWorker(context);
        startFetchSyncTeacherWorker(context);
        startFetchSyncSchoolClassesWorker(context);
    }

    public static void startUploadWorkers(Context context) {
        startUploadSyncClockInUploadWorker(context);
        startUploadSyncClockOutUploadWorker(context);
    }

    // picking data from the cloud to SyncTimeTable table
    public static void startFetchSyncTimeTableWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncTimeTableWorker.class, 1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);
    }

    // picking data from the cloud to SyncTeacher table
    public static void startFetchSyncTeacherWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncTeacherWorker.class, 1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);

    }

    // picking data from the cloud to SyncSchoolClasses table
    public static void startFetchSyncSchoolClassesWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncSchoolClassesWorker.class, 1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);

    }

    //Upload clock in content to server
    public static  void startUploadSyncClockInUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncClockInTeacherUploadWorker.class , 1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);
    }

    //Upload clock out content to server
    public static  void startUploadSyncClockOutUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncClockOutTeacherUploadWorker.class, 1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);

    }

}
