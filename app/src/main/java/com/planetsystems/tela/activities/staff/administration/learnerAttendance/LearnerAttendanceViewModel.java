package com.planetsystems.tela.activities.staff.administration.learnerAttendance;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.LearnersEnrolled.LearnersEnrolled;
import com.planetsystems.tela.data.LearnersEnrolled.LearnersEnrolledRepository;
import com.planetsystems.tela.data.attendance.LearnerRepository;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;
import com.planetsystems.tela.data.schoolClasses.SchoolClassesRepository;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;

import java.util.List;

public class LearnerAttendanceViewModel extends AndroidViewModel {
    private SchoolClassesRepository schoolClassesRepository;
    private LearnerRepository learnerRepository;
    private LearnersEnrolledRepository learnersEnrolledRepository;

    public LearnerAttendanceViewModel(@NonNull Application application) {
        super(application);

        schoolClassesRepository = MainRepository.getInstance(application).getSchoolClassesRepository();
        learnerRepository = MainRepository.getInstance(application).getLearnerRepository();
        learnersEnrolledRepository = MainRepository.getInstance(application).getLearnersEnrolledRepository();
    }

    ///////////////////Learner classes/////////////////////////////////////
    public LiveData<List<SyncSchoolClasses>> schoolClasses() {
        return schoolClassesRepository.getAllClassesInSchool();
    }

    /////////////////////////////Learner attendance///////////////////////////
    public void learnerAttendance(SyncAttendanceRecord syncAttendanceRecord){
         learnerRepository.insertLearnerAttendance(syncAttendanceRecord);
    }

    public LiveData<List<SyncAttendanceRecord>> learnerRecords(){
        return learnerRepository.getAllLearnerRecords();
    }

    public LiveData<List<SyncAttendanceRecord>> getAttendanceByClassAndDate(String classUnit, String date){
        return learnerRepository.getAttendanceByClassAndDate(classUnit, date);
    }

    //////////Learners enrolled/////////////////////////////////////////
    public void insertLearnersEnrolled(LearnersEnrolled learnersEnrolled){
        learnersEnrolledRepository.insertLearnersEnrolled(learnersEnrolled);
    }

    public LiveData<List<LearnersEnrolled>> getLearnersEnrolled() {
        return learnersEnrolledRepository.getLearnersEnrolled();
    }

}
