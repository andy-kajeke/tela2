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
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDMDao;

import java.util.List;


@SuppressWarnings("ALL")
public class SyncTeacherTimeOffRequestUploadWorker extends Worker {
    SyncEmployeeTimeOffRequestDMDao syncEmployeeTimeOffRequestDMDao;
    public SyncTeacherTimeOffRequestUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncEmployeeTimeOffRequestDMDao = telaRoomDatabase.getSyncEmployeeTimeOffRequestDMsDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<SyncEmployeeTimeOffRequestDM> syncEmployeeTimeOffRequestDMS = syncEmployeeTimeOffRequestDMDao.getTeacherRequestRecords(false);
        for(SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM: syncEmployeeTimeOffRequestDMS) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncEmployeeTimeOffRequestDM.toString());
            // TODO: upload each individual timeOffRequest in to the backend
            try {
                syncEmployeeTimeOffRequestDM.getEmployeeNo();
                syncEmployeeTimeOffRequestDM.getEmployeeRequestType();
                syncEmployeeTimeOffRequestDM.getComment();
                syncEmployeeTimeOffRequestDM.getRequestDate();
                syncEmployeeTimeOffRequestDM.getSchoolId();
                syncEmployeeTimeOffRequestDM.getApprovalStatus();
                syncEmployeeTimeOffRequestDM.getFromDate();
                syncEmployeeTimeOffRequestDM.getToDate();
                syncEmployeeTimeOffRequestDM.getFromTime();
                syncEmployeeTimeOffRequestDM.getToTime();
                syncEmployeeTimeOffRequestDM.getRequestId();
                syncEmployeeTimeOffRequestDM.getSupervisor();

                String resp = Urls.POST(Urls.TIME_OFF_REQUEST_URL, new Gson().toJson(syncEmployeeTimeOffRequestDM));
                if (resp == Urls.DID_WORK) {
                    syncEmployeeTimeOffRequestDM.setUploadedTeacher(true);
                    syncEmployeeTimeOffRequestDMDao.update(syncEmployeeTimeOffRequestDM);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
