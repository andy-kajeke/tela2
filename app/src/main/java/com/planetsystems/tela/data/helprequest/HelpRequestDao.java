package com.planetsystems.tela.data.helprequest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDMsConstants;

import java.util.List;

@Dao
public interface HelpRequestDao {

    @Insert
    void addNewHelpRequest(HelpRequest helpRequest);

    @Query("SELECT * FROM " + HelpRequestConstant.TABLE_NAME)
    LiveData<List<HelpRequest>> getHelpRequests();

    @Query("SELECT * FROM " + HelpRequestConstant.TABLE_NAME +
            " WHERE "
            + HelpRequestConstant.APPROVED_STATUS +
            " =:approvalStatus "
    )
    LiveData<List<HelpRequest>> getRequestByApprovalStatus (String approvalStatus);
}
