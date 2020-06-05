package com.planetsystems.tela.workers.upload;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.planetsystems.tela.activities.staff.regularStaff.home.TeacherHomeActivity;
import com.planetsystems.tela.constants.Urls;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.timeOnTask.EmployeeTasks;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTaskDao;

import java.util.List;


@SuppressWarnings("ALL")
public class SyncTeacherTimeOnTaskAttendanceUploadWorker extends Worker {
    SynTimeOnTaskDao synTimeOnTaskDao;
    TeacherHomeActivity teacherHomeActivity = new TeacherHomeActivity();
    public SyncTeacherTimeOnTaskAttendanceUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        synTimeOnTaskDao = telaRoomDatabase.getSyncTimeOnTaskDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<SynTimeOnTask> synTimeOnTasks = synTimeOnTaskDao.getTeacherRecords(false);
        for(SynTimeOnTask synTimeOnTask: synTimeOnTasks) {
            Log.d(getClass().getSimpleName(), "Uploading: " + synTimeOnTask.toString());
            // TODO: upload each individual syncTimeOnTask in to the backend
            try {
                EmployeeTasks employeeTasks = new EmployeeTasks();

                employeeTasks.setEmployeeId(synTimeOnTask.getEmployeeId());
                employeeTasks.setEmployeeNumber(synTimeOnTask.getEmployeeNumber());
                employeeTasks.setTasks(teacherHomeActivity.taskList());

                String resp = Urls.POST(Urls.LEARNER_ATTENDANCE_UPLOAD_URL, new Gson().toJson(employeeTasks));
                if (resp == Urls.DID_WORK) {
                    synTimeOnTask.setUploaded_teacher(true);
                    synTimeOnTaskDao.update(synTimeOnTask);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
