package com.planetsystems.tela.workers.upload;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.planetsystems.tela.constants.Urls;
import com.planetsystems.tela.data.LearnersEnrolled.LearnersEnrolled;
import com.planetsystems.tela.data.LearnersEnrolled.LearnersEnrolledDao;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.schoolMaterialRequests.SchoolMaterialRequests;
import com.planetsystems.tela.data.schoolMaterialRequests.SchoolMaterialRequestsDao;

import java.util.List;


@SuppressWarnings("ALL")
public class SyncTeacherMaterialRequestUploadWorker extends Worker {
    SchoolMaterialRequestsDao schoolMaterialRequestsDao;
    public SyncTeacherMaterialRequestUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        schoolMaterialRequestsDao = telaRoomDatabase.getSchoolMaterialRequestsDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<SchoolMaterialRequests> schoolMaterialRequests = schoolMaterialRequestsDao.getTeacherRequestRecords(false);
        for(SchoolMaterialRequests schoolMaterialRequests1: schoolMaterialRequests) {
            Log.d(getClass().getSimpleName(), "Uploading: " + schoolMaterialRequests1.toString());
            // TODO: upload enrolled learners into the backend
            try {
                schoolMaterialRequests1.getEmployeeNo();
                schoolMaterialRequests1.getEmployeeRequestType();
                schoolMaterialRequests1.getComment();
                schoolMaterialRequests1.getRequestDate();
                schoolMaterialRequests1.getSchoolId();
                schoolMaterialRequests1.getItemId();
                schoolMaterialRequests1.getQuantity();
                schoolMaterialRequests1.getDateRequired();
                schoolMaterialRequests1.getApprovalStatus();
                schoolMaterialRequests1.getRequestId();
                schoolMaterialRequests1.getSupervisor();

                String resp = Urls.POST(Urls.SCHOOL_MATERIAL_REQUEST_UPLOAD_URE, new Gson().toJson(schoolMaterialRequests1));
                if (resp == Urls.DID_WORK) {
                    schoolMaterialRequests1.setUploadedTeacher(true);
                    schoolMaterialRequestsDao.update(schoolMaterialRequests1);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
