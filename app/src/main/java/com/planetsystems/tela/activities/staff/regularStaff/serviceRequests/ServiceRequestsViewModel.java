package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.attendance.LearnerRepository;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.TimeOffRequestRepository;
import com.planetsystems.tela.data.helprequest.HelpRequest;
import com.planetsystems.tela.data.helprequest.HelpRequestRepository;
import com.planetsystems.tela.data.schoolClasses.SchoolClassesRepository;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.schoolMaterialRequests.SchoolMaterialRequestRepository;
import com.planetsystems.tela.data.schoolMaterialRequests.SchoolMaterialRequests;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterials;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterialsRepository;

import java.util.List;

public class ServiceRequestsViewModel extends AndroidViewModel {
    private TimeOffRequestRepository timeOffRequestRepository;
    private HelpRequestRepository helpRequestRepository;
    SchoolMaterialsRepository schoolMaterialsRepository;
    SchoolMaterialRequestRepository schoolMaterialRequestRepository;

    public ServiceRequestsViewModel(@NonNull Application application) {
        super(application);

        timeOffRequestRepository = MainRepository.getInstance(application).getTimeOffRequestRepository();
        helpRequestRepository = MainRepository.getInstance(application).getHelpRequestRepository();
        schoolMaterialsRepository = MainRepository.getInstance(application).getSchoolMaterialsRepository();
        schoolMaterialRequestRepository = MainRepository.getInstance(application).getSchoolMaterialRequestRepository();
    }

    /////////////////////////////////Time off-leave request/////////////////////////////////////////////

    public void addNewTimeOffRequest(SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM){
        timeOffRequestRepository.addNewTimeOffRequest(syncEmployeeTimeOffRequestDM);
    }

    public void updateLeaveApprovalStatus(String approvalStatus, String approvalDate, String supervisorID, int primaryKey){
        timeOffRequestRepository.updateLeaveApprovalStatus(approvalStatus, approvalDate, supervisorID, primaryKey);
    }

    public LiveData<List<SyncEmployeeTimeOffRequestDM>> getAllTimeOffs(){
        return timeOffRequestRepository.getAllRequests();
    }

    public LiveData<List<SyncEmployeeTimeOffRequestDM>> getAllTimeOffs(String requestType, String approvalStatus){
        return timeOffRequestRepository.getRequestByTypeAndApprovalStatus(requestType, approvalStatus);
    }

    public LiveData<List<SyncEmployeeTimeOffRequestDM>> getApprovalStatusByEmployeeNo(String requestType, String employeeNo){
        return timeOffRequestRepository.getApprovalStatusByEmployeeNo(requestType, employeeNo);
    }

    public LiveData<List<SyncEmployeeTimeOffRequestDM>> getAllMeetings(String requestType, String approvalStatus){
        return timeOffRequestRepository.getRequestByTypeAndApprovalStatus(requestType, approvalStatus);
    }

    public LiveData<List<SyncEmployeeTimeOffRequestDM>> getRequestByApprovalStatus(String approvalStatus){
        return timeOffRequestRepository.getRequestByApprovalStatus(approvalStatus);
    }

    /////////////////////////Help request///////////////////////////////////////////////////////

    public LiveData<List<HelpRequest>> getAllHelpRequests(final String approvalStatus){
        return helpRequestRepository.getRequestByApprovalStatus(approvalStatus);
    }

    public LiveData<List<HelpRequest>> getRequestsByEmployeeNo(final String employeeNo){
        return helpRequestRepository.getRequestsByEmployeeNo(employeeNo);
    }

    public void addHelpRequest(HelpRequest helpRequest){
        helpRequestRepository.addNewHelpRequest(helpRequest);
    }

    public void updateHelpApprovalStatus(String approvalStatus, String approvalDate, String supervisorID, int primaryKey){
        helpRequestRepository.updateHelpApprovalStatus(approvalStatus, approvalDate, supervisorID, primaryKey);
    }

    ///////////////////////////////////School material request///////////////////////////////////////////////////////////////

    public LiveData<List<SchoolMaterials>> getAllSchoolMaterials(){
        return schoolMaterialsRepository.getAllMaterials();
    }

    public void addNewMaterialRequest(SchoolMaterialRequests schoolMaterialRequests){
        schoolMaterialRequestRepository.addNewMaterialRequest(schoolMaterialRequests);
    }

    public void updateMaterialApprovalStatus(String approvalStatus,String approvalDate, String supervisorID, int primaryKey){
        schoolMaterialRequestRepository.updateMaterialApprovalStatus(approvalStatus, approvalDate, supervisorID, primaryKey);
    }

    public LiveData<List<SchoolMaterialRequests>> getMaterialRequests(){
        return schoolMaterialRequestRepository.getMaterialRequests();
    }

    public LiveData<List<SchoolMaterialRequests>> getMaterialRequestByApprovalStatus(final String approvalStatus){
        return schoolMaterialRequestRepository.getRequestByApprovalStatus(approvalStatus);
    }

    public LiveData<List<SchoolMaterialRequests>> getMaterialRequestsByEmployeeNo(final String employeeNo){
        return schoolMaterialRequestRepository.getRequestsByEmployeeNo(employeeNo);
    }
}
