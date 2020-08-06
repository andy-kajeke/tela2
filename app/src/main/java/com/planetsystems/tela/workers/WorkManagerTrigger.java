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
import com.planetsystems.tela.workers.upload.SyncConfirmTimeOnSiteAttendanceUploadWorker;
import com.planetsystems.tela.workers.upload.SyncLeanersEnrrolledUploadWorker;
import com.planetsystems.tela.workers.upload.SyncLearnerAttendanceUploadWorker;
import com.planetsystems.tela.workers.upload.SyncSmcObservationsUploadWorker;
import com.planetsystems.tela.workers.upload.SyncSupervisorTimeOffRequestUploadWorker;
import com.planetsystems.tela.workers.upload.SyncSupervisorTimeOnTaskAttendanceUploadWorker;
import com.planetsystems.tela.workers.upload.SyncTeacherTimeOffRequestUploadWorker;
import com.planetsystems.tela.workers.upload.SyncTeacherTimeOnTaskAttendanceUploadWorker;
import com.planetsystems.tela.workers.upload.SyncTeacherUploadWorker;
import com.planetsystems.tela.workers.upload.SyncTeachersEnrrolledUploadWorker;

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
        startUploadSyncLearnerAttendanceUploadWorker(context);
        startUploadSyncConfirmTimeOnSiteAttendanceUploadWorker(context);
        startUploadSyncTeacherTimeOnTaskAttendanceUploadWorker(context);
        startUploadSyncSupervisorTimeOnTaskAttendanceUploadWorker(context);
        startUploadSyncSmcObservationsUploadWorker(context);
        startUploadSyncTeacherUploadWorker(context);
        startUploadSyncTeachersEnrolledUploadWorker(context);
        startUploadSyncLeanersEnrolledUploadWorker(context);
        startUploadSyncTeacherTimeOffRequestUploadWorker(context);
        startUploadSyncSupervisorTimeOffRequestUploadWorker(context);
    }

    // picking data from the cloud to SyncTimeTable table
    public static void startFetchSyncTimeTableWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncTimeTableWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);
    }

    // picking data from the cloud to SyncTeacher table
    public static void startFetchSyncTeacherWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncTeacherWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);

    }

    // picking data from the cloud to SyncSchoolClasses table
    public static void startFetchSyncSchoolClassesWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncSchoolClassesWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);

    }

    //Upload clock in content to server
    public static  void startUploadSyncClockInUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncClockInTeacherUploadWorker.class , 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);
    }

    //Upload clock out content to server
    public static  void startUploadSyncClockOutUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncClockOutTeacherUploadWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);

    }

    //Upload learner attendance content to server
    public static  void startUploadSyncLearnerAttendanceUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncLearnerAttendanceUploadWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);

    }

    //Upload enrolled teachers content to server
    public static  void startUploadSyncTeachersEnrolledUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncTeachersEnrrolledUploadWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);

    }

    //Upload enrolled teachers content to server
    public static  void startUploadSyncLeanersEnrolledUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncLeanersEnrrolledUploadWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);

    }

    //Upload ConfirmTimeOnSiteAttendance content to server
    public static  void startUploadSyncConfirmTimeOnSiteAttendanceUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncConfirmTimeOnSiteAttendanceUploadWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);

    }

    //Upload TeacherTimeOnTaskAttendance content to server
    public static  void startUploadSyncTeacherTimeOnTaskAttendanceUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncTeacherTimeOnTaskAttendanceUploadWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);

    }

    //Upload SupervisorTimeOnTaskAttendance content to server
    public static  void startUploadSyncSupervisorTimeOnTaskAttendanceUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncSupervisorTimeOnTaskAttendanceUploadWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);

    }

    //Upload Smc Records content to server
    public static  void startUploadSyncSmcObservationsUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncSmcObservationsUploadWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);

    }

    //Upload clock in content to server
    public static  void startUploadSyncTeacherUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncTeacherUploadWorker.class , 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);
    }

    //Upload leave request in content to server
    public static  void startUploadSyncTeacherTimeOffRequestUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncTeacherTimeOffRequestUploadWorker.class , 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);
    }

    //Upload supervisor confirmation request in content to server
    public static  void startUploadSyncSupervisorTimeOffRequestUploadWorker(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncSupervisorTimeOffRequestUploadWorker.class , 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);
    }

}