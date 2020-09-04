package com.planetsystems.tela.workers.fetch;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClassesDao;
import com.planetsystems.tela.utils.DynamicData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.planetsystems.tela.constants.Urls.SCHOOL_CLASSES;
import static com.planetsystems.tela.constants.Urls.SYNC_TEACHER_URL;

public class SyncSchoolClassesWorker extends Worker {
    private SyncSchoolClassesDao syncSchoolClassesDao;
    private String TAG = getClass().getSimpleName();

    public SyncSchoolClassesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncSchoolClassesDao = telaRoomDatabase.getSyncSchoolClassesDao();
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(SCHOOL_CLASSES + DynamicData.getSchoolID(getApplicationContext())).openConnection();
            try {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                SyncClasses syncClasses = new Gson().fromJson(reader, SyncClasses.class);
                reader.close();
                for(int i = 0; i < syncClasses.schoolClasses.size(); i++) {
                    SyncSchoolClasses syncSchoolClasses = syncClasses.schoolClasses.get(i);
                    SyncSchoolClasses syncedClasses = syncSchoolClassesDao.getSyncSchool(
                            syncSchoolClasses.getClassName(),
                            syncSchoolClasses.getCode(),
                            syncSchoolClasses.getId()
                    );

                    if ( syncedClasses == null ) {
                        syncSchoolClassesDao.insertSchoolClasses(syncSchoolClasses);
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

    public static class SyncClasses {
        List<SyncSchoolClasses> schoolClasses;
    }
}
