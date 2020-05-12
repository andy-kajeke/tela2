package com.planetsystems.tela.activities.staff.administration.editStaff;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;

import java.util.List;

public class EditStaffListViewModel extends AndroidViewModel {
    private String TAG =this.getClass().getSimpleName();
    private SyncTeacherDao syncTeacherDao;
    private MainRepository mainRepository;

    public EditStaffListViewModel(@NonNull Application application) {
        super(application);

        mainRepository = new MainRepository(application);
    }
    public LiveData<List<SyncTeacher>> teachers(){
        return mainRepository.getAllTeachers();
    }
}
