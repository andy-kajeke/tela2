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
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecordDao;
import com.planetsystems.tela.data.smc.SyncSMC;
import com.planetsystems.tela.data.smc.SyncSMCDao;

import java.util.List;


@SuppressWarnings("ALL")
public class SyncSmcObservationsUploadWorker extends Worker {
    SyncSMCDao syncSMCDao;
    public SyncSmcObservationsUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncSMCDao = telaRoomDatabase.getSyncSMCDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<SyncSMC> syncSMCS = syncSMCDao.getUnSyncedRecords(false);
        for(SyncSMC syncSMC: syncSMCS) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncSMC.toString());
            // TODO: upload each individual syncclock in to the backend
            try {
                syncSMC.getDeploymentUnitId();
                syncSMC.getSmcCode();
                syncSMC.getStaffPresent();
                syncSMC.getStaffTeaching();
                syncSMC.getStaffNotTeaching();
                syncSMC.getP1();
                syncSMC.getP2();
                syncSMC.getP3();
                syncSMC.getP4();
                syncSMC.getP5();
                syncSMC.getP6();
                syncSMC.getP7();
                syncSMC.getHeadTeacherPresent();
                syncSMC.getVisitDate();
                syncSMC.getComment();

                String resp = Urls.POST(Urls.SMC_SUPERVISION_URL, new Gson().toJson(syncSMC));
                if (resp == Urls.DID_WORK) {
                    syncSMC.setUploaded(true);
                    syncSMCDao.update(syncSMC);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
