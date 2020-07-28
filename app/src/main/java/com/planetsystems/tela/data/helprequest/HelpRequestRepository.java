package com.planetsystems.tela.data.helprequest;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDMDao;

import java.util.List;

public class HelpRequestRepository {
    private static HelpRequestRepository INSTANCE;
    private HelpRequestDao helpRequestDao;

    private HelpRequestRepository(TelaRoomDatabase telaRoomDatabase) {
        helpRequestDao = telaRoomDatabase.getHelpRequestDao();
    }

    public static HelpRequestRepository getInstance(final TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (HelpRequestRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new HelpRequestRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void addNewHelpRequest(final HelpRequest helpRequest){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                helpRequestDao.addNewHelpRequest(helpRequest);
            }
        });
    }

    public void updateHelpApprovalStatus(final String approvalStatus, final String approvalDate, final String supervisorID, final int primaryKey){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                helpRequestDao.update(approvalStatus, approvalDate, supervisorID, primaryKey);
            }
        });
    }

    public LiveData<List<HelpRequest>> getRequestsByEmployeeNo(final String employeeNo){
        return helpRequestDao.getRequestByEmployeeNo(employeeNo);
    }

    public LiveData<List<HelpRequest>> getRequestByApprovalStatus(final String approvalStatus){
        return helpRequestDao.getRequestByApprovalStatus(approvalStatus);
    }
}
