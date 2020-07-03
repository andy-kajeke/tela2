package com.planetsystems.tela.workers.upload;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.TelaRoomDatabase;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//            String dataForUpload = new Gson().toJson(teacher);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

            JSONObject params = new JSONObject();
            try {
                params.put("id",teacher.getId());
                params.put("nationalId", teacher.getNationalId());
                params.put("firstName", teacher.getFirstName());
                params.put("lastName",teacher.getLastName());
                params.put("licensed",teacher.isLicensed());
                params.put("phoneNumber",teacher.getPhoneNumber());
                params.put("emailAddress",teacher.getEmailAddress());
                params.put("schoolId",teacher.getSchoolId());
                params.put("initials",teacher.getInitials());
                params.put("gender",teacher.getGender());
                params.put("dob", teacher.getDob());
                params.put("MPSComputerNumber", teacher.getMPSComputerNumber());
                params.put("role",teacher.getRole());
            } catch (JSONException e) {
                e.printStackTrace();

            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ENROLL_URL, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Response", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Respone", error.toString());
                }
            });

            queue.add(jsonObjectRequest);
        }
//        Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();
        return Result.success();
    }

}
