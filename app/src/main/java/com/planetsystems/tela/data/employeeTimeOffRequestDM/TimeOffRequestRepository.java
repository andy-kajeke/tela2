package com.planetsystems.tela.data.employeeTimeOffRequestDM;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;

public class TimeOffRequestRepository {
    private static TimeOffRequestRepository INSTANCE;
    private SyncEmployeeTimeOffRequestDMDao syncEmployeeTimeOffRequestDMDao;

    private TimeOffRequestRepository(TelaRoomDatabase telaRoomDatabase) {
        syncEmployeeTimeOffRequestDMDao = telaRoomDatabase.getSyncEmployeeTimeOffRequestDMsDao();
    }

    public static TimeOffRequestRepository getInstance(final TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (TimeOffRequestRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new TimeOffRequestRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void addNewTimeOffRequest(final SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncEmployeeTimeOffRequestDMDao.addNewRequest(syncEmployeeTimeOffRequestDM);
            }
        });
    }

    public void updateLeaveApprovalStatus(final String approvalStatus, final String approvalDate, final int primaryKey){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncEmployeeTimeOffRequestDMDao.update(approvalStatus, approvalDate, primaryKey);
            }
        });
    }

    public LiveData<List<SyncEmployeeTimeOffRequestDM>> getAllRequests(){
        return syncEmployeeTimeOffRequestDMDao.getAllRecords();
    }

    public LiveData<List<SyncEmployeeTimeOffRequestDM>> getRequestByTypeAndApprovalStatus(final String requestType, final String approvalStatus){
        return syncEmployeeTimeOffRequestDMDao.getRequestTypeByApprovalStatus(requestType, approvalStatus);
    }

    public LiveData<List<SyncEmployeeTimeOffRequestDM>> getApprovalStatusByEmployeeNo(final String requestType, final String employeeNo){
        return syncEmployeeTimeOffRequestDMDao.getApprovalStatusByEmployeeNo(requestType, employeeNo);
    }

    public LiveData<List<SyncEmployeeTimeOffRequestDM>> getRequestByApprovalStatus(final String approvalStatus){
        return syncEmployeeTimeOffRequestDMDao.getRequestByApprovalStatus(approvalStatus);
    }
}
