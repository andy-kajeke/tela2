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
import com.planetsystems.tela.data.TeachersEnrolled.TeachersEnrolled;
import com.planetsystems.tela.data.TeachersEnrolled.TeachersEnrolledDao;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecordDao;

import java.util.List;


@SuppressWarnings("ALL")
public class SyncTeachersEnrrolledUploadWorker extends Worker {
    TeachersEnrolledDao teachersEnrolledDao;
    public SyncTeachersEnrrolledUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        teachersEnrolledDao = telaRoomDatabase.getTeachersEnrolledDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<TeachersEnrolled> teachersEnrolleds = teachersEnrolledDao.getTeachersRecords(false);
        for(TeachersEnrolled teachersEnrolled: teachersEnrolleds) {
            Log.d(getClass().getSimpleName(), "Uploading: " + teachersEnrolled.toString());
            // TODO: upload each individual syncclock in to the backend
            try {
                teachersEnrolled.getSchoolId();
                teachersEnrolled.getDeviceNo();
                teachersEnrolled.getTotalMale();
                teachersEnrolled.getTotalFemale();

                String resp = Urls.POST(Urls.ENROLLED_TEACHERS_URL, new Gson().toJson(teachersEnrolled));
                if (resp == Urls.DID_WORK) {
                    teachersEnrolled.setIs_uploaded(true);
                    teachersEnrolledDao.update(teachersEnrolled);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
