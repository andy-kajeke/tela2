package com.planetsystems.tela.constants;

import android.util.Log;

import com.planetsystems.tela.utils.DynamicData;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.Objects;

import static com.planetsystems.tela.activities.mainActivity.MainActivity.SchoolDeviceIMEINumber;

@SuppressWarnings("ALL")
public class Urls {
    public static final String DEVICE_IMEI = DynamicData.getSchoolID(SchoolDeviceIMEINumber);

    public static final String BASE_URL = "http://tela.planetsystems.co:8080/weca/webapi/attendance/";

    public static final String DEVICE_OWNERSHIP =  BASE_URL + "school/";

    public static final String SYNC_TEACHER_URL = BASE_URL + "teachers/" + DEVICE_IMEI;

    public static final String CONFIRM_TASKS = BASE_URL + "confirmtasks";

    public static final String SUPERVISOR_CONFIRMATION_ON_TASKS = BASE_URL + "confirmteacherclassattendence";

    public static final String CLOCK_IN_UPLOAD_URL = BASE_URL + "clockin";

    public static final String ATTENDANCE_ON_SITE_UPLOAD_URL = BASE_URL + "confirmteacherschoolattendence";

    public static final String CLOCK_OUT_UPLOAD_URL = BASE_URL + "clockout";

    public static final String SYNC_TIME_TABLE_URL = BASE_URL + "timetable/" + DEVICE_IMEI;

    public static final String SCHOOL_CLASSES = BASE_URL + "classes/" + DEVICE_IMEI;

    public static final String LEARNER_ATTENDANCE_UPLOAD_URL = BASE_URL + "learners/attendance";

    public static final String SMC_SUPERVISION_URL = BASE_URL + "smcsupervision";

    public static final String ENROLL_URL = BASE_URL + "teachers";

    public static final String ENROLLED_TEACHERS_URL = BASE_URL + "teachersenrolled";

    public static final String DID_WORK = "Work Done!";

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
