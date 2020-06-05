package com.planetsystems.tela.workers.upload;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.TelaRoomDatabase;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static com.planetsystems.tela.constants.Urls.DID_WORK;
import static com.planetsystems.tela.constants.Urls.ENROLL_URL;

public class SyncTeacherUploadWorker extends Worker {
    SyncTeacherDao syncTeacherDao;
    public SyncTeacherUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        syncTeacherDao = TelaRoomDatabase.getInstance(context).getSyncTeachersDao();
    }

    @NonNull
    @Override
    public Result doWork() {
        List<SyncTeacher> syncTeachers = syncTeacherDao.getListStoredLocally(true);
        for (SyncTeacher teacher: syncTeachers) {
            // upload
            String dataForUpload = new Gson().toJson(teacher);
            String result = POST(ENROLL_URL, dataForUpload);
            if (result.equals(DID_WORK)) {
                syncTeacherDao.deleteStaff(teacher);
            }
        }
//        Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();
        return Result.success();
    }

    //uploading content to server
    private static String POST(String url, String jsontasks){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);


            // 5. set json to StringEntity
            StringEntity se = new StringEntity(jsontasks);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                //result = convertInputStreamToString(inputStream);
                result = DID_WORK;
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", Objects.requireNonNull(e.getLocalizedMessage()));
        }

        // 11. return result
        return result;
    }
}
