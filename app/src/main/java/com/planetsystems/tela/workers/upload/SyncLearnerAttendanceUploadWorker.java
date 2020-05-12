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
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecordDao;

import java.util.List;


@SuppressWarnings("ALL")
public class SyncLearnerAttendanceUploadWorker extends Worker {
    SyncAttendanceRecordDao syncAttendanceRecordDao;
    public SyncLearnerAttendanceUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncAttendanceRecordDao = telaRoomDatabase.getSyncAttendanceRecordsDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<SyncAttendanceRecord> syncAttendanceRecords = syncAttendanceRecordDao.getLearnerRecords(false);
        for(SyncAttendanceRecord syncAttendanceRecord: syncAttendanceRecords) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncAttendanceRecord.toString());
            // TODO: upload each individual syncclock in to the backend
            try {
                syncAttendanceRecord.getDeploymentUnitId();
                syncAttendanceRecord.getDeploymentUnit();
                syncAttendanceRecord.getMalePresent();
                syncAttendanceRecord.getFemalePresent();
                syncAttendanceRecord.getMaleAbsent();
                syncAttendanceRecord.getFemaleAbsent();
                syncAttendanceRecord.getComment();
                syncAttendanceRecord.getSubmissionDate();
                syncAttendanceRecord.getTaskDay();
                syncAttendanceRecord.getSupervisorId();

                String resp = Urls.POST(Urls.LEARNER_ATTENDANCE, new Gson().toJson(syncAttendanceRecord));
                if (resp == Urls.DID_WORK) {
                    syncAttendanceRecord.setUploaded(true);
                    syncAttendanceRecordDao.update(syncAttendanceRecord);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
