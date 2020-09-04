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
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;


@SuppressWarnings("ALL")
public class SyncTimeTableUpdateUploadWorker extends Worker {
    SyncTimeTableDao syncTimeTableDao;
    public SyncTimeTableUpdateUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncTimeTableDao = telaRoomDatabase.getSyncTimeTableDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<SyncTimeTable> syncTimeTables = syncTimeTableDao.getTimeTableUpdate(false);
        for(SyncTimeTable syncTimeTable: syncTimeTables) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncTimeTable.toString());
            // TODO: upload each individual syncclock in to the backend
            try {
                syncTimeTable.getTaskId();
                syncTimeTable.getEmployeeNo();
                syncTimeTable.getDay();
                syncTimeTable.getClassId();
                syncTimeTable.getSubjectId();
                syncTimeTable.getStartTime();
                syncTimeTable.getEndTime();

                String resp = Urls.POST(Urls.SYNC_TIME_TABLE_UPDATE_URL, new Gson().toJson(syncTimeTable));
                if (resp == Urls.DID_WORK) {
                    syncTimeTable.setIs_updated(true);
                    syncTimeTableDao.update(syncTimeTable);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
