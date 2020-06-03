package com.planetsystems.tela.activities.fingerprint;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.TeacherRepository;

import java.util.List;

/*
* The activity class can be a Lifecycle of observer, i was supposed to use viewmodel
* but i can instantiate it in the activity extends activity not appcompatactivity
* am here by trying to emulate view model but a fake one
* */
public class FingerPrintActivityViewModel {
    private TeacherRepository repository;
    private List<SyncTeacher> syncTeachers;

    public FingerPrintActivityViewModel(@NonNull Application application) {
         repository = MainRepository.getInstance(application).getTeachersRepository();
         syncTeachers = repository.getTeachers();
    }

    public void enrollTeacher(SyncTeacher teacher) {
        for (SyncTeacher syncTeacher: syncTeachers) {
            if (syncTeacher.equals(teacher)) {
                return;
            }
        }
    }
}
