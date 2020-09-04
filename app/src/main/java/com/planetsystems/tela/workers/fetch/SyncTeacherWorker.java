package com.planetsystems.tela.workers.fetch;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.utils.DynamicData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.SchoolDeviceIMEINumber;
import static com.planetsystems.tela.constants.Urls.SYNC_TEACHER_URL;

public class SyncTeacherWorker extends Worker {
    private SyncTeacherDao syncTeacherDao;
    private Context context;

    public SyncTeacherWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncTeacherDao = telaRoomDatabase.getSyncTeachersDao();
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
<<<<<<< HEAD
            HttpURLConnection connection = (HttpURLConnection) new URL(SYNC_TEACHER_URL + DynamicData.getSchoolID(SchoolDeviceIMEINumber)).openConnection();
=======
            HttpURLConnection connection = (HttpURLConnection) new URL(SYNC_TEACHER_URL + DynamicData.getSchoolID(context)).openConnection();
>>>>>>> 9ac0c9ad84d4e407977b7fc7c2bda54c0bfb3572
            try {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                SyncTeachers syncTeachers = new Gson().fromJson(reader, SyncTeachers.class);
                reader.close();
                for(int i = 0; i < syncTeachers.teachers.size(); i++) {
                    SyncTeacher teacher = syncTeachers.teachers.get(i);
                    SyncTeacher syncedTeacher = syncTeacherDao.getSyncTeacher(teacher.getNationalId());
                    if ( syncedTeacher == null ) {
                        syncTeacherDao.enrollTeacher(teacher);
                    }
                }
                return Result.success();
            } catch (IOException e) {
            Log.e(getClass().getSimpleName(), "Exception parsing JSON", e);
            } finally {
                connection.disconnect();
            }
        }  catch (IOException e) {
        Log.e(getClass().getSimpleName(), "Exception parsing JSON", e);
        }
        return Result.retry();
    }

    public static class SyncTeachers {
        List<SyncTeacher> teachers;
    }
}
