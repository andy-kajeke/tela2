package com.planetsystems.tela.workers.upload;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.planetsystems.tela.constants.Urls;
import com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance.SyncConfirmTimeOnSiteAttendance;
import com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance.SyncConfirmTimeOnSiteAttendanceDao;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecordDao;

import java.util.List;


@SuppressWarnings("ALL")
public class SyncConfirmTimeOnSiteAttendanceUploadWorker extends Worker {
    SyncConfirmTimeOnSiteAttendanceDao syncConfirmTimeOnSiteAttendanceDao;
    public SyncConfirmTimeOnSiteAttendanceUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncConfirmTimeOnSiteAttendanceDao = telaRoomDatabase.getSyncConfirmTimeOnSiteAttendanceDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<SyncConfirmTimeOnSiteAttendance> syncConfirmTimeOnSiteAttendances = syncConfirmTimeOnSiteAttendanceDao.getConfirmationRecords(false);
        for(SyncConfirmTimeOnSiteAttendance syncConfirmTimeOnSiteAttendance: syncConfirmTimeOnSiteAttendances) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncConfirmTimeOnSiteAttendance.toString());
            // TODO: upload each individual syncclock in to the backend
            try {
                syncConfirmTimeOnSiteAttendance.getEmployeeId();
                syncConfirmTimeOnSiteAttendance.getEmployeeNo();
                syncConfirmTimeOnSiteAttendance.getSupervisionDay();
                syncConfirmTimeOnSiteAttendance.getSupervisionId();
                syncConfirmTimeOnSiteAttendance.getSupervisionComment();
                syncConfirmTimeOnSiteAttendance.getSupervisionStatus();
                syncConfirmTimeOnSiteAttendance.getSupervisionDate();

                String resp = Urls.POST(Urls.ATTENDANCE_ON_SITE_UPLOAD_URL, new Gson().toJson(syncConfirmTimeOnSiteAttendance));
                if (resp == Urls.DID_WORK) {
                    syncConfirmTimeOnSiteAttendance.setUploaded(true);
                    syncConfirmTimeOnSiteAttendanceDao.update(syncConfirmTimeOnSiteAttendance);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
