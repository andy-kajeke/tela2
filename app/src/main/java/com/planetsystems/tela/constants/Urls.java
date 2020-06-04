package com.planetsystems.tela.constants;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.Objects;

@SuppressWarnings("ALL")
public class Urls {
    public static final String DEVICE_IMEI = "354633111523205";

    public static final String BASE_URL = "http://tela.planetsystems.co:8080/weca/webapi/attendance/";

    public static final String DEVICE_OWNERSHIP =  BASE_URL + "school/";

    public static final String SYNC_TEACHER_URL = BASE_URL + "teachers/" + DEVICE_IMEI;

    public static final String CLOCK_IN_UPLOAD_URL = BASE_URL + "clockin";

    public static final String CLOCK_OUT_UPLOAD_URL = BASE_URL + "clockout";

    public static final String SYNC_TIME_TABLE_URL = BASE_URL + "timetable/" + DEVICE_IMEI;

    public static final String SCHOOL_CLASSES = BASE_URL + "classes/" + DEVICE_IMEI;

    public static final String LEARNER_ATTENDANCE_UPLOAD_URL = BASE_URL + "learners/attendance";

    public static final String DID_WORK = "Work Done!";

    public static final String ENROLL_URL = "http://tela.planetsystems.co:8080/weca/webapi/attendance/teachers";

    //uploading content to server
    public static String POST(String url, String jsontasks){
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
