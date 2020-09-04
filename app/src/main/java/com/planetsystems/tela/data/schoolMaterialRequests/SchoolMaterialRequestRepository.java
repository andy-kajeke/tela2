package com.planetsystems.tela.data.schoolMaterialRequests;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.helprequest.HelpRequest;
import com.planetsystems.tela.data.helprequest.HelpRequestDao;

import java.util.List;

public class SchoolMaterialRequestRepository {
    private static SchoolMaterialRequestRepository INSTANCE;
    private SchoolMaterialRequestsDao schoolMaterialRequestsDao;

    private SchoolMaterialRequestRepository(TelaRoomDatabase telaRoomDatabase) {
        schoolMaterialRequestsDao = telaRoomDatabase.getSchoolMaterialRequestsDao();
    }

    public static SchoolMaterialRequestRepository getInstance(final TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (SchoolMaterialRequestRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new SchoolMaterialRequestRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void addNewMaterialRequest(final SchoolMaterialRequests schoolMaterialRequests){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                schoolMaterialRequestsDao.insertMaterialRequest(schoolMaterialRequests);
            }
        });
    }

    public void updateMaterialApprovalStatus(final String approvalStatus, final String approvalDate,final String supervisorID, final int primaryKey){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                schoolMaterialRequestsDao.update(approvalStatus, approvalDate, supervisorID, primaryKey);
            }
        });
    }

    public LiveData<List<SchoolMaterialRequests>> getMaterialRequests(){
        return schoolMaterialRequestsDao.getAllMaterialRequest();
    }

    public LiveData<List<SchoolMaterialRequests>> getRequestByApprovalStatus(final String approvalStatus){
        return schoolMaterialRequestsDao.getRequestByApprovalStatus(approvalStatus);
    }

    public LiveData<List<SchoolMaterialRequests>> getRequestsByEmployeeNo(final String employeeNo){
        return schoolMaterialRequestsDao.getRequestByEmployeeNo(employeeNo);
    }
}
