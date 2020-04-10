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

    public SyncClockIn clockInTeacherEmployeeNumber(List<SyncTeacher> teacherList, String employeeNumber) {
        // example employee number 9876 for ojok
        /*
        * This method clocks in a teacher and returns the results
        * */
        SyncClockIn syncClockIn = null;
        for (SyncTeacher teacher: teacherList) {
            if (teacher.getEmployeeNumber().equals(employeeNumber)) {
                Log.d("Clock in re", teacher.toString());
                syncClockIn = new SyncClockIn(
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
            }

        }
        return syncClockIn ;
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
