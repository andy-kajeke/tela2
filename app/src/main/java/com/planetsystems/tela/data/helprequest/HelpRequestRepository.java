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

    public void  addNewHelpRequest(final HelpRequest helpRequest){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                helpRequestDao.addNewHelpRequest(helpRequest);
            }
        });
    }

    public LiveData<List<HelpRequest>> getAllRequests(){
        return helpRequestDao.getHelpRequests();
    }

    public LiveData<List<HelpRequest>> getRequestByApprovalStatus(final String approvalStatus){
        return helpRequestDao.getRequestByApprovalStatus(approvalStatus);
    }
}
