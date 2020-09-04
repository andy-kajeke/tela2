package com.planetsystems.tela.activities.test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.clockOut.ClockOutRepository;
import com.planetsystems.tela.data.clockOut.SyncClockOut;
import com.planetsystems.tela.data.clockOut.SyncClockOutDao;
import com.planetsystems.tela.data.schoolClasses.SchoolClassesRepository;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClassesDao;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterials;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterialsRepository;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;

public class TestActivityViewModel extends AndroidViewModel {
    SyncTeacherDao syncTeacherDao;
    ClockOutRepository clockOutRepository;
    SchoolClassesRepository schoolClassesRepository;
    SchoolMaterialsRepository schoolMaterialsRepository;
    public TestActivityViewModel(@NonNull Application application) {
        super(application);
        //MainRepository mainRepository = MainRepository.getInstance(application);
//        syncTeacherDao = new MainRepository(application).getSyncTeacherDao();
//        syncTimeTableDao = new MainRepository(application).getSyncTimeTableDao();
        schoolClassesRepository = MainRepository.getInstance(application).getSchoolClassesRepository();
        schoolMaterialsRepository = MainRepository.getInstance(application).getSchoolMaterialsRepository();
        clockOutRepository = MainRepository.getInstance(application).getClockOutRepository();
    }

    LiveData<List<SyncSchoolClasses>> schoolClasses() {
        return schoolClassesRepository.getAllClassesInSchool();
    }

    public LiveData<List<SchoolMaterials>> getAllMaterials(){
        return schoolMaterialsRepository.getAllMaterials();
    }

    public LiveData<List<SyncClockOut>> allClockOuts(){
        return clockOutRepository.allClockOuts();
    }
}
