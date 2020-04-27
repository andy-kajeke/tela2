package com.planetsystems.tela.workers;

import android.content.Context;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.planetsystems.tela.workers.fetch.SyncTeacherWorker;
import com.planetsystems.tela.workers.fetch.SyncTimeTableWorker;
import com.planetsystems.tela.workers.upload.SyncClockInTeacherUploadWorker;

import java.util.Calendar;

public class WorkManagerTrigger {
    public static  void startFetchWorkers(Context context) {
        startFetchSyncTimeTableWorker(context);
        startFetchSyncTeacherWorker(context);
    }

    public static void startUploadWorkers(Context context) {
        startUploadSyncClockInUploadWorker(context);
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

    public static  void startUploadSyncClockInUploadWorker(Context context) {
        Calendar currentDateAndTime = Calendar.getInstance();
        Calendar nextRunDateAndTime = Calendar.getInstance();

        nextRunDateAndTime.set(Calendar.HOUR_OF_DAY, 5);
        nextRunDateAndTime.set(Calendar.MINUTE, 0);
        nextRunDateAndTime.set(Calendar.SECOND, 0);

        if (nextRunDateAndTime.before(currentDateAndTime)) {
            nextRunDateAndTime.add(Calendar.HOUR_OF_DAY, 24);
        }

        long timeDifference = nextRunDateAndTime.getTimeInMillis() -
                currentDateAndTime.getTimeInMillis();


        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SyncClockInTeacherUploadWorker.class)
                .setConstraints(constraints)
                // TODO: The line bellow must be uncommented during project, this was commented out for testing
//                .setInitialDelay(timeDifference, TimeUnit.MILLISECONDS)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);
    }

    // picking data from the cloud to SyncTeacher table
    public static void startFetchSyncTeacherWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SyncTeacherWorker.class)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);

    }
}
