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
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.Teacher.TeacherRepository;
import com.planetsystems.tela.data.TelaRoomDatabase;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.planetsystems.tela.constants.Urls.ENROLL_URL;

public class SyncTeacherUploadWorker extends Worker {
    SyncTeacherDao syncTeacherDao;
    TeacherRepository teacherRepository;
    public SyncTeacherUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        syncTeacherDao = TelaRoomDatabase.getInstance(context).getSyncTeachersDao();
        teacherRepository = TeacherRepository.getInstance(TelaRoomDatabase.getInstance(context));
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
                        SyncTeacher savedTeacher = teacherRepository.getTeacherWithNationalIDNumber(teacher.getString("nationalId"));
                        if (savedTeacher != null) {
                            savedTeacher.setSchoolId(teacher.getString("schoolId"));
                            savedTeacher.setMPSComputerNumber(teacher.getString("MPSComputerNumber"));
                            savedTeacher.setEmailAddress(teacher.getString("emailAddress"));
                            savedTeacher.setEmployeeNumber(teacher.getString("employeeNumber"));
                            savedTeacher.setFirstName(teacher.getString("firstName"));
                            savedTeacher.setGender(teacher.getString("gender"));
                            savedTeacher.setId(teacher.getString("id"));
                            savedTeacher.setInitials(teacher.getString("initials"));
                            savedTeacher.setLastName(teacher.getString("lastName"));
                            savedTeacher.setLicensed(teacher.getBoolean("licensed"));
                            savedTeacher.setNationalId(teacher.getString("nationalId"));
                            savedTeacher.setPhoneNumber(teacher.getString("phoneNumber"));
                            savedTeacher.setRole(teacher.getString("role"));
                            savedTeacher.setStoredLocally(false);
                            teacherRepository.updateTeacher(savedTeacher);
                            Log.d(getClass().getSimpleName(), "Done");
                        }
                    } catch (JSONException | InterruptedException | ExecutionException e) {
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
