package com.planetsystems.tela.activities.clockInAndOutActivity;

import android.app.Application;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClockInAndOutActivityViewModel extends AndroidViewModel {
    LocationManager locationManager;
    double lng;
    double lat;

    String checkIn_time;
    String dateString;
    String dayOfTheWeek;

    private LiveData<List<SyncTeacher>> syncTeachers;
    private Repository repository;

    public ClockInAndOutActivityViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
        syncTeachers = repository.getAllTeachers();

        //Day of the week
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);

        //Date of the day
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        dateString = dateFormat.format(date);
    }

    LiveData<List<SyncTeacher>> getAllSyncTeacher() {
        return syncTeachers;
    }

    LiveData<List<SyncClockIn>> getAlreadyClockedInTeachersToday() {
        // TODO: Must be implemented
        return null;
    }

    SyncTeacher clockInTeacherEmployeeNumber(List<SyncTeacher> teacherList, String employeeNumber) {
        // example employee number 9876 for ojok
        /*
        * This method clocks in a teacher and returns the results
        * */
       SyncTeacher syncTeacher = null;
        for (SyncTeacher teacher: teacherList) {
            if (teacher.getEmployeeNumber().equals(employeeNumber)) {
                Log.d("Clock in re", teacher.toString());
                SyncClockIn syncClockIn = new SyncClockIn(
                       teacher.getId(),
                       null,
                       null,
                       null,
                        dateString,
                       null,
                        dayOfTheWeek,
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
                syncTeacher = teacher;
            }

        }
        return syncTeacher ;
    }

    public String findEmployeeNumberWithStaffID(List<SyncTeacher> teachers, String staffID) {
        for (SyncTeacher teacher: teachers ) {
            if (teacher.getId().equals(staffID)) {
                return teacher.getEmployeeNumber();
            }
        }
        return "";
    }

    SyncTeacher clockOutTeacherWithFingerPrint(List<SyncTeacher> teacherList, byte[] fingerPrintData) {
        return null;
    }
}
