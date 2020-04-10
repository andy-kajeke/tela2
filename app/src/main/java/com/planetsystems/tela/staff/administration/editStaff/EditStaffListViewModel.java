package com.planetsystems.tela.staff.administration.editStaff;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.Teacher.SyncTeacherModel;
import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.List;

public class EditStaffListViewModel extends AndroidViewModel {
    private String TAG =this.getClass().getSimpleName();
    private SyncTeacherDao syncTeacherDao;
    private Repository repository;
    private LiveData<List<SyncTeacherModel>> mAllStaff;

    public EditStaffListViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
    }
    public LiveData<List<SyncTeacher>> teachers(){
        return repository.getAllTeachers();
    }
}
