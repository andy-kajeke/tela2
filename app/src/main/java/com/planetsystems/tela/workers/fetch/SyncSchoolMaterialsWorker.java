package com.planetsystems.tela.workers.fetch;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClassesDao;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterials;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterialsDao;
import com.planetsystems.tela.utils.DynamicData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.SchoolDeviceIMEINumber;
import static com.planetsystems.tela.constants.Urls.SCHOOL_CLASSES;
import static com.planetsystems.tela.constants.Urls.SCHOOL_MATERIALS_URL;

public class SyncSchoolMaterialsWorker extends Worker {
    private SchoolMaterialsDao schoolMaterialsDao;
    private String TAG = getClass().getSimpleName();

    public SyncSchoolMaterialsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        schoolMaterialsDao = telaRoomDatabase.getSchoolMaterialsDao();
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(SCHOOL_MATERIALS_URL).openConnection();
            try {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                SyncMaterials syncMaterials = new Gson().fromJson(reader, SyncMaterials.class);
                reader.close();
                for(int i = 0; i < syncMaterials.items.size(); i++) {
                    SchoolMaterials schoolMaterials = syncMaterials.items.get(i);
                    SchoolMaterials schoolMaterials1 = schoolMaterialsDao.getSchoolMaterials(
                            schoolMaterials.getItemName(),
                            schoolMaterials.getCode(),
                            schoolMaterials.getId()
                    );

                    if ( schoolMaterials1 == null ) {
                        schoolMaterialsDao.insertSchoolMaterials(schoolMaterials);
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

    public static class SyncMaterials {
        List<SchoolMaterials> items;
    }
}
