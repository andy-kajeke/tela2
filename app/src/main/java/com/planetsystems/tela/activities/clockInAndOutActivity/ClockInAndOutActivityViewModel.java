package com.planetsystems.tela.activities.clockInAndOutActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;

public class ClockInAndOutActivityViewModel extends AndroidViewModel {
    private LiveData<List<SyncTeacher>> syncTeachers;
    private Repository repository;
    public ClockInAndOutActivityViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
        syncTeachers = repository.getAllTeachers();
    }

    public LiveData<List<SyncTeacher>> getAllSyncTeacher() {
        return syncTeachers;
    }

    public boolean lockInTeacherWithID(List<SyncTeacher> teacherList, String requireNonNull) {
        for (SyncTeacher teacher: teacherList) {
            if (teacher.getId().equals(requireNonNull)) {

            }
        }

    }
}
