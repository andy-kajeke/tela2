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
import com.planetsystems.tela.data.TeachersEnrolled.TeachersEnrolled;
import com.planetsystems.tela.data.TeachersEnrolled.TeachersEnrolledDao;
import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.List;


@SuppressWarnings("ALL")
public class SyncLeanersEnrrolledUploadWorker extends Worker {
    LearnersEnrolledDao learnersEnrolledDao;
    public SyncLeanersEnrrolledUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        learnersEnrolledDao = telaRoomDatabase.getLearnersEnrolledDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<LearnersEnrolled> learnersEnrolleds = learnersEnrolledDao.getLearnersRecords(false);
        for(LearnersEnrolled learnersEnrolled: learnersEnrolleds) {
            Log.d(getClass().getSimpleName(), "Uploading: " + learnersEnrolled.toString());
            // TODO: upload enrolled learners into the backend
            try {
                learnersEnrolled.getClassId();
                learnersEnrolled.getTotalMale();
                learnersEnrolled.getTotalFemale();
                learnersEnrolled.getSubmissionDate();

                String resp = Urls.POST(Urls.ENROLLED_LEARNERS_URL_UPLOAD, new Gson().toJson(learnersEnrolled));
                if (resp == Urls.DID_WORK) {
                    learnersEnrolled.setIs_uploaded(true);
                    learnersEnrolledDao.update(learnersEnrolled);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
