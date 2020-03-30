package com.planetsystems.tela.activities.enrollActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

public class EnrollmentActivityViewModel extends AndroidViewModel {
    private Repository repository;
    public EnrollmentActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

    }

    public void enrollTeacher(SyncTeacher syncTeacher) {
        repository.enrollTeacher(syncTeacher);
    }
}
