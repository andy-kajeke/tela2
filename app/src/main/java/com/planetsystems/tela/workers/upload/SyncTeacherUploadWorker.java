package com.planetsystems.tela.workers.upload;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.TelaRoomDatabase;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.xml.transform.Result;

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
        final boolean[] haveError = {false};
        List<SyncTeacher> syncTeachers = syncTeacherDao.getListStoredLocally(true);
        for (SyncTeacher teacher: syncTeachers) {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

            JSONObject params = new JSONObject();
            try {
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
                    try {
                        Log.d(getClass().getSimpleName(), "LOADING some data in the DB");
                        JSONObject teacher = response.getJSONObject("teacher");
                        SyncTeacher savedTeacher = syncTeacherDao.findTeacherWithNationalID(teacher.getString("nationalId"));
                        SyncTeacher syncTeacher = new SyncTeacher.Builder()
                                .setPrimaryKey(savedTeacher.getPrimaryKey())
                                .setSchoolID(teacher.getString("schoolId"))
                                .setMPSComputerNumber(teacher.getString("MPSComputerNumber"))
                                .setEmailAddress(teacher.getString("emailAddress"))
                                .setEmployeeNumber(teacher.getString("employeeNumber"))
                                .setFirstName(teacher.getString("firstName"))
                                .setGender(teacher.getString("gender"))
                                .setID(teacher.getString("id"))
                                .setInitials(teacher.getString("initials"))
                                .setLastName(teacher.getString("lastName"))
                                .setLicensed(teacher.getBoolean("licensed"))
                                .setNationalID(teacher.getString("nationalId"))
                                .setPhoneNumber(teacher.getString("phoneNumber"))
                                .setRole(teacher.getString("role"))
                                .setSchoolID(teacher.getString("schoolId"))
                                .setIsStoredLocally(false)
                                .build();
                        syncTeacherDao.updateStaff(syncTeacher);
                        Log.d(getClass().getSimpleName(), "Done");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("Response", response.toString());
                    // end of the request
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Response", error.toString());
                    Toast.makeText(getApplicationContext(), "Error Enrolling Teacher", Toast.LENGTH_SHORT).show();
                    haveError[0] = true;
                }
            });

            queue.add(jsonObjectRequest);
        }

        if (haveError[0]) {
            return Result.retry();
        }
        return Result.success();
    }

}
