package com.planetsystems.tela.staff.administration.learnerAttendance;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
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

        schoolClassesRepository = new MainRepository(application).getSchoolClassesRepository();
        learnerRepository = new MainRepository(application).getLearnerRepository();
    }
    LiveData<List<SyncSchoolClasses>> schoolClasses() {
        return schoolClassesRepository.getAllClassesInSchool();
    }

    public void learnerAttendance(SyncAttendanceRecord syncAttendanceRecord){
         learnerRepository.insertLearnerAttendance(syncAttendanceRecord);
    }

    LiveData<List<SyncAttendanceRecord>> learnerRecords(){
        return learnerRepository.getAllLearnerRecords();
    }
}
