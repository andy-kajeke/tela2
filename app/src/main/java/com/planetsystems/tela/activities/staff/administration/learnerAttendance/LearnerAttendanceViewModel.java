package com.planetsystems.tela.activities.staff.administration.learnerAttendance;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.attendance.LearnerRepository;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;
import com.planetsystems.tela.data.schoolClasses.SchoolClassesRepository;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;

import java.util.List;

public class LearnerAttendanceViewModel extends AndroidViewModel {
    private SchoolClassesRepository schoolClassesRepository;
    private LearnerRepository learnerRepository;

    public LearnerAttendanceViewModel(@NonNull Application application) {
        super(application);

        schoolClassesRepository = MainRepository.getInstance(application).getSchoolClassesRepository();
        learnerRepository = MainRepository.getInstance(application).getLearnerRepository();
    }
    public LiveData<List<SyncSchoolClasses>> schoolClasses() {
        return schoolClassesRepository.getAllClassesInSchool();
    }

    public void learnerAttendance(SyncAttendanceRecord syncAttendanceRecord){
         learnerRepository.insertLearnerAttendance(syncAttendanceRecord);
    }

    public LiveData<List<SyncAttendanceRecord>> learnerRecords(){
        return learnerRepository.getAllLearnerRecords();
    }
}
