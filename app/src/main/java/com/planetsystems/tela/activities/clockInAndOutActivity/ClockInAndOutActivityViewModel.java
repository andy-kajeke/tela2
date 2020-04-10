package com.planetsystems.tela.activities.clockInAndOutActivity;

import android.app.Application;
import android.location.LocationManager;

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
    private List<SyncTeacher> teachers;
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

    SyncTeacher clockInTeacherEmployeeNumber(String employeeNumber) {
        // example employee number 9876 for ojok
        /*
        * This method clocks in a teacher and returns the results
        * */
       SyncTeacher syncTeacher = findEmployeeNumberWithEmployeeNumber(employeeNumber);
       if (syncTeacher == null) return null;
       SyncClockIn syncClockIn = copySynTeacherToSyncClockIn(syncTeacher);
       repository.synClockInTeacher(syncClockIn);
        return syncTeacher ;
    }

    private SyncTeacher findEmployeeNumberWithEmployeeNumber(String staffID) {
        for (SyncTeacher teacher: teachers ) {
            if (teacher.getId().equals(staffID)) {
                return teacher;
            }
        }
        return null;
    }


    SyncTeacher clockOutTeacherWithFingerPrint(String stringEncodedFingerPrint, String base64EncodedBitmapImage) {
        SyncTeacher syncTeacher = findTeacherWithFingerPrint(stringEncodedFingerPrint.getBytes());
        if (syncTeacher == null) return null;
        SyncClockIn syncClockIn = copySynTeacherToSyncClockIn(syncTeacher);
        repository.synClockOutTeacher(syncClockIn);
        return syncTeacher;
    }

    SyncTeacher clockInTeacherWithFingerPrint(String stringEncodedFingerPrint, String base64EncodedBitmapImage) {
        SyncTeacher syncTeacher = findTeacherWithFingerPrint(stringEncodedFingerPrint.getBytes());
        if (syncTeacher == null ) return null;
        SyncClockIn syncClockIn = copySynTeacherToSyncClockIn(syncTeacher);
        repository.synClockInTeacher(syncClockIn);
        return syncTeacher;
    }

    List<SyncTeacher> getTeachers() {
        return teachers;
    }

    void setTeachers(List<SyncTeacher> teachers) {
        this.teachers = teachers;
    }

    private SyncTeacher findTeacherWithFingerPrint(byte[] fingerPrint) {
        if (teachers == null) return null;
        for (SyncTeacher teacher: teachers) {
            if (teacher.getFingerPrint().getBytes() == fingerPrint) {
                return teacher;
            }
        }
        return null;
    }

    private SyncClockIn copySynTeacherToSyncClockIn(SyncTeacher teacher) {
        // TODO: please fix the time below, this time will be time the app was lunched change it
        return new SyncClockIn(
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
    }
}
