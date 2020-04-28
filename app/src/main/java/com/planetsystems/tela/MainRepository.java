package com.planetsystems.tela;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.ClockIn.ClockInRepository;;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.Teacher.TeacherRepository;
import com.planetsystems.tela.data.clockOut.ClockOutRepository;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;

public class MainRepository {
    private static MainRepository INSTANCE;
    private SyncTimeTableDao syncTimeTableDao;
    private SyncClockInDao syncClockInDao;

    private ClockInRepository clockInRepository;
    private ClockOutRepository clockOutRepository;
    private TeacherRepository teacherRepository;

    public MainRepository(Application application) {
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(application);
        syncTimeTableDao = telaRoomDatabase.getSyncTimeTableDao();
        clockInRepository = ClockInRepository.getInstance(telaRoomDatabase);
        clockOutRepository = ClockOutRepository.getInstance(telaRoomDatabase);
        teacherRepository = TeacherRepository.getInstance(telaRoomDatabase);

    }


    // made it singleton
    public static MainRepository getInstance(final Application  application) {
        if (INSTANCE == null) {
            synchronized (MainRepository.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = new MainRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    //Enroll new staff member
    public void enrollTeacher(final SyncTeacher syncTeacher){
        teacherRepository.insertSyncTeacher(syncTeacher);
    }

    //Fetch all enrolled staff members
    public LiveData<List<SyncTeacher>> getAllTeachers(){
        return teacherRepository.getAllTeachers();
    }

    //Get all timetables
    public LiveData<List<SyncTimeTable>> getAllSyncTimeTable(){
        return syncTimeTableDao.getSyncTimeTables();
    }

    public LiveData<List<SyncClockIn>> getClockedInByDateOfDay (String dateOfDay){
        return clockInRepository.getClockedInTeachersByDate(dateOfDay);
    }

    public SyncClockInDao getSyncClockInDao() {
        return syncClockInDao;
    }

    public SyncTimeTableDao getSyncTimeTableDao() {
        return syncTimeTableDao;
    }


    public LiveData<List<SyncTeacher>> getAllSyncTeacher() {
        return teacherRepository.getAllTeachers();
    }


    public ClockInRepository getClockInRepository() {
        return clockInRepository;
    }

    public ClockOutRepository getClockOutRepository() {
        return clockOutRepository;
    }

    public TeacherRepository getTeachersRepository() {
        return teacherRepository;
    }
}
