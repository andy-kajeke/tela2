package com.planetsystems.tela.workers.upload;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.planetsystems.tela.activities.staff.regularStaff.home.TeacherHomeActivity;
import com.planetsystems.tela.constants.Urls;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.confirmTimeOnTaskAttendance.SyncConfirmTimeOnTaskAttendance;
import com.planetsystems.tela.data.confirmTimeOnTaskAttendance.SyncConfirmTimeOnTaskAttendanceDao;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTaskDao;

import java.util.List;


@SuppressWarnings("ALL")
public class SyncSupervisorTimeOnTaskAttendanceUploadWorker extends Worker {
    SyncConfirmTimeOnTaskAttendanceDao syncConfirmTimeOnTaskAttendanceDao;
    public SyncSupervisorTimeOnTaskAttendanceUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncConfirmTimeOnTaskAttendanceDao = telaRoomDatabase.getSyncConfirmTimeOnTaskAttendancesDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<SyncConfirmTimeOnTaskAttendance> syncConfirmTimeOnTaskAttendances = syncConfirmTimeOnTaskAttendanceDao.getSupervisorRecords(false);
        for(SyncConfirmTimeOnTaskAttendance syncConfirmTimeOnTaskAttendance: syncConfirmTimeOnTaskAttendances) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncConfirmTimeOnTaskAttendance.toString());
            // TODO: upload each individual syncTimeOnTask in to the backend
            try {

                syncConfirmTimeOnTaskAttendance.getTaskId();
                syncConfirmTimeOnTaskAttendance.getEmployeeId();
                syncConfirmTimeOnTaskAttendance.getEmployeeNumber();
                syncConfirmTimeOnTaskAttendance.getSupervisionStatus();
                syncConfirmTimeOnTaskAttendance.getComment();
                syncConfirmTimeOnTaskAttendance.getTransactionDate();
                syncConfirmTimeOnTaskAttendance.getSupervisorId();

                String resp = Urls.POST(Urls.SUPERVISOR_CONFIRMATION_ON_TASKS, new Gson().toJson(syncConfirmTimeOnTaskAttendance));
                if (resp == Urls.DID_WORK) {
                    syncConfirmTimeOnTaskAttendance.setUploaded(true);
                    syncConfirmTimeOnTaskAttendanceDao.update(syncConfirmTimeOnTaskAttendance);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
