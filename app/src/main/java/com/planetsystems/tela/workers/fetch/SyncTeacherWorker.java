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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.planetsystems.tela.constants.Urls.SYNC_TEACHER_URL;

public class SyncTeacherWorker extends Worker {
    private SyncTeacherDao syncTeacherDao;
    public SyncTeacherWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncTeacherDao = telaRoomDatabase.getSyncTeachersDao();
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("Worker", "Beginning work =================================================");
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(SYNC_TEACHER_URL).openConnection();
            try {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                SyncTeachers syncTeachers = new Gson().fromJson(reader, SyncTeachers.class);
                reader.close();
                for(int i = 0; i < syncTeachers.teachers.size(); i++) {
                    syncTeacherDao.enrollTeacher(syncTeachers.teachers.get(i));
                    Log.d(getClass().getSimpleName(), "Save teacher" + syncTeachers.teachers.get(i).toString());
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
