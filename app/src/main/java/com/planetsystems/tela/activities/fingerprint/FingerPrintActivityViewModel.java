package com.planetsystems.tela.activities.fingerprint;

import android.app.Application;

import androidx.annotation.NonNull;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.TeacherRepository;
import com.suprema.IBioMiniDevice;

import java.util.List;

/*
* The activity class can be a Lifecycle of observer, i was supposed to use viewmodel
* but i can instantiate it in the activity extends activity not appcompatactivity
* am here by trying to emulate view model but a fake one
* */
public class FingerPrintActivityViewModel {
    private TeacherRepository repository;
    private List<SyncTeacher> syncTeachers;

    FingerPrintActivityViewModel(@NonNull Application application) {
         repository = MainRepository.getInstance(application).getTeachersRepository();
         syncTeachers = repository.getTeachers();
    }

    public boolean enrollTeacher(SyncTeacher teacher, IBioMiniDevice mCurrentDevice) {
        boolean enrolled = false;
        for (SyncTeacher syncTeacher: syncTeachers) {
            if ((mCurrentDevice.verify(teacher.getFingerPrint(), syncTeacher.getFingerPrint())) || (syncTeacher.getNationalId().equals(teacher.getNationalId()))) {
                enrolled = true;
            }
        }

        if (!enrolled) {
            repository.insertSyncTeacher(teacher);
            return true;
        }
        return false;
    }
}
