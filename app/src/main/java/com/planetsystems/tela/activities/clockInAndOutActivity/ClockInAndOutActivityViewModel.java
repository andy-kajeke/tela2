package com.planetsystems.tela.activities.clockInAndOutActivity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
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

    public boolean clockInTeacherEmployeeNumber(List<SyncTeacher> teacherList, String employeeNumber) {
        // example employee number 9876 for ojok
        for (SyncTeacher teacher: teacherList) {
            if (teacher.getEmployeeNumber().equals(employeeNumber)) {
                Log.d("Clock in re", teacher.toString());
                SyncClockIn syncClockIn = new SyncClockIn(
                       teacher.getId(),
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       teacher.getEmployeeNumber(),
                        teacher.getEmployeeNumber(),
                        null,
                        null,
                        null,
                        null,
                        teacher.getFirstName(),
                        teacher.getFirstName(),
                        null,
                        null

                );
                repository.synClockInTeacherWithID(syncClockIn);
                return true;
            }
            return false;

        }
        return true;
    }

    public String findEmployeeNumberWithStaffID(List<SyncTeacher> teachers, String staffID) {
        for (SyncTeacher teacher: teachers ) {
            if (teacher.getId().equals(staffID)) {
                return teacher.getEmployeeNumber();
            }
        }
        return "";
    }
}
