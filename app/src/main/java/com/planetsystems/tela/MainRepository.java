package com.planetsystems.tela;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.ClockIn.ClockInRepository;;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance.TimeOnSiteAttendanceRepository;
import com.planetsystems.tela.data.LearnersEnrolled.LearnersEnrolledRepository;
import com.planetsystems.tela.data.Teacher.TeacherRepository;
import com.planetsystems.tela.data.TeachersEnrolled.TeachersEnrolledRepository;
import com.planetsystems.tela.data.attendance.LearnerRepository;
import com.planetsystems.tela.data.clockOut.ClockOutRepository;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.confirmTimeOnTaskAttendance.ConfirmTimeOnTaskRepository;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.TimeOffRequestRepository;
import com.planetsystems.tela.data.helprequest.HelpRequestRepository;
import com.planetsystems.tela.data.schoolClasses.SchoolClassesRepository;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClassesDao;
import com.planetsystems.tela.data.schoolMaterialRequests.SchoolMaterialRequestRepository;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterialsRepository;
import com.planetsystems.tela.data.smc.SmcRepository;
import com.planetsystems.tela.data.timeOnTask.TimeOnTaskRepository;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;
import com.planetsystems.tela.data.timetable.TimeTableRepository;
import com.planetsystems.tela.data.logs.ExecutionLogRepository;

import java.util.List;

public class MainRepository {
    private static MainRepository INSTANCE;
    private SyncTimeTableDao syncTimeTableDao;

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
    private SmcRepository smcRepository;
    private TeachersEnrolledRepository teachersEnrolledRepository;
    private LearnersEnrolledRepository learnersEnrolledRepository;
    private SchoolMaterialsRepository schoolMaterialsRepository;
    private SchoolMaterialRequestRepository schoolMaterialRequestRepository;
    private ExecutionLogRepository executionLogRepository;

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
        smcRepository = SmcRepository.getInstance(telaRoomDatabase);
        teachersEnrolledRepository = TeachersEnrolledRepository.getInstance(telaRoomDatabase);
        learnersEnrolledRepository = LearnersEnrolledRepository.getInstance(telaRoomDatabase);
        schoolMaterialsRepository = SchoolMaterialsRepository.getInstance(telaRoomDatabase);
        schoolMaterialRequestRepository = SchoolMaterialRequestRepository.getInstance(telaRoomDatabase);
        executionLogRepository = ExecutionLogRepository.getInstance(telaRoomDatabase);

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

    public TimeOnTaskRepository getTimeOnTaskRepository() { return timeOnTaskRepository; }

    public ConfirmTimeOnTaskRepository getConfirmTimeOnTaskRepository() { return confirmTimeOnTaskRepository; }

    public SchoolClassesRepository getSchoolClassesRepository(){
        return schoolClassesRepository;
    }

    public LearnerRepository getLearnerRepository() {return learnerRepository;}

    public TimeOnSiteAttendanceRepository getTimeOnSiteAttendanceRepository() { return timeOnSiteAttendanceRepository; }

    public TimeTableRepository getTimeTableRepository() { return timeTableRepository; }

    public TimeOffRequestRepository getTimeOffRequestRepository() { return timeOffRequestRepository; }

    public HelpRequestRepository getHelpRequestRepository() { return helpRequestRepository; }

    public SmcRepository getSmcRepository() { return smcRepository; }

    public TeachersEnrolledRepository getTeachersEnrolledRepository() {return teachersEnrolledRepository;}

    public LearnersEnrolledRepository getLearnersEnrolledRepository(){return learnersEnrolledRepository;}

    public SchoolMaterialsRepository getSchoolMaterialsRepository() {
        return schoolMaterialsRepository;
    }

    public SchoolMaterialRequestRepository getSchoolMaterialRequestRepository() {
        return schoolMaterialRequestRepository;
    }

    public ExecutionLogRepository getExecutionLogRepository() {
        return executionLogRepository;
    }
}
