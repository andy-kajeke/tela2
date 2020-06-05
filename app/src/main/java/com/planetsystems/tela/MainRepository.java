package com.planetsystems.tela;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.ClockIn.ClockInRepository;;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance.TimeOnSiteAttendanceRepository;
import com.planetsystems.tela.data.Teacher.TeacherRepository;
import com.planetsystems.tela.data.attendance.LearnerRepository;
import com.planetsystems.tela.data.clockOut.ClockOutRepository;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.confirmTimeOnTaskAttendance.ConfirmTimeOnTaskRepository;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.TimeOffRequestRepository;
import com.planetsystems.tela.data.helprequest.HelpRequestDao;
import com.planetsystems.tela.data.helprequest.HelpRequestRepository;
import com.planetsystems.tela.data.schoolClasses.SchoolClassesRepository;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClassesDao;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timeOnTask.TimeOnTaskRepository;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;
import com.planetsystems.tela.data.timetable.TimeTableRepository;

import java.util.List;

public class MainRepository {
    private static MainRepository INSTANCE;
    private SyncTimeTableDao syncTimeTableDao;
    private SyncClockInDao syncClockInDao;
    private SyncSchoolClassesDao syncSchoolClassesDao;

    private ClockInRepository clockInRepository;
    private ClockOutRepository clockOutRepository;
    private TeacherRepository teacherRepository;
    private TimeOnTaskRepository timeOnTaskRepository;
    private SchoolClassesRepository schoolClassesRepository;
    private LearnerRepository learnerRepository;
    private TimeOnSiteAttendanceRepository timeOnSiteAttendanceRepository;
    private TimeTableRepository timeTableRepository;
    private TimeOffRequestRepository timeOffRequestRepository;
    private HelpRequestRepository helpRequestRepository;
    private ConfirmTimeOnTaskRepository confirmTimeOnTaskRepository;

    private MainRepository(Application application) {
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(application);
        syncTimeTableDao = telaRoomDatabase.getSyncTimeTableDao();
        clockInRepository = ClockInRepository.getInstance(telaRoomDatabase);
        clockOutRepository = ClockOutRepository.getInstance(telaRoomDatabase);
        teacherRepository = TeacherRepository.getInstance(telaRoomDatabase);
        timeOnTaskRepository = TimeOnTaskRepository.getInstance(telaRoomDatabase);
        schoolClassesRepository = SchoolClassesRepository.getInstance(telaRoomDatabase);
        learnerRepository = LearnerRepository.getInstance(telaRoomDatabase);
        timeOnSiteAttendanceRepository = TimeOnSiteAttendanceRepository.getInstance(telaRoomDatabase);
        timeTableRepository = TimeTableRepository.getInstance(telaRoomDatabase);
        timeOffRequestRepository = TimeOffRequestRepository.getInstance(telaRoomDatabase);
        helpRequestRepository = HelpRequestRepository.getInstance(telaRoomDatabase);
        confirmTimeOnTaskRepository = ConfirmTimeOnTaskRepository.getInstance(telaRoomDatabase);

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

    public TimeOnTaskRepository getTimeOnTaskRepository(){return timeOnTaskRepository;}

    public ConfirmTimeOnTaskRepository getConfirmTimeOnTaskRepository(){return confirmTimeOnTaskRepository;}

    public SchoolClassesRepository getSchoolClassesRepository(){
        return schoolClassesRepository;
    }

    public LearnerRepository getLearnerRepository(){return learnerRepository;}

    public TimeOnSiteAttendanceRepository getTimeOnSiteAttendanceRepository(){return timeOnSiteAttendanceRepository;}

    public TimeTableRepository getTimeTableRepository(){return timeTableRepository;}

    public TimeOffRequestRepository getTimeOffRequestRepository(){return timeOffRequestRepository;}

    public HelpRequestRepository getHelpRequestRepository(){return helpRequestRepository;}
}
