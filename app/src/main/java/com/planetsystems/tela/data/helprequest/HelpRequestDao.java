package com.planetsystems.tela.data.helprequest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDMsConstants;

import java.util.List;

@Dao
public interface HelpRequestDao {

    @Insert
    void addNewHelpRequest(HelpRequest helpRequest);

    @Query("SELECT * FROM " + HelpRequestConstant.TABLE_NAME)
    LiveData<List<HelpRequest>> getHelpRequests();

    @Update
    void update(HelpRequest helpRequest);

    @Query("SELECT * FROM " + HelpRequestConstant.TABLE_NAME +
            " WHERE "
            + HelpRequestConstant.APPROVAL_STATUS +
            " =:approvalStatus "
    )
    LiveData<List<HelpRequest>> getRequestByApprovalStatus (String approvalStatus);

    @Query("SELECT * FROM " + HelpRequestConstant.TABLE_NAME +
            " WHERE "
            + HelpRequestConstant.STAFF_CODE +
            " =:employeeNo "
    )
    LiveData<List<HelpRequest>> getRequestByEmployeeNo (String employeeNo);

    @Query("UPDATE " + HelpRequestConstant.TABLE_NAME
            + " SET "
            + HelpRequestConstant.APPROVAL_STATUS + " =:approvalStatus"
            + ","
            + HelpRequestConstant.SUPERVISOR + " =:supervisorID"
            + ","
            + HelpRequestConstant.APPROVAL_DATE + " =:approvalDate"
            + " WHERE "
            + HelpRequestConstant.PRIMARY_KEY + " =:primaryKey")
    void update(String approvalStatus, String approvalDate, String supervisorID, int primaryKey);

    @Query("SELECT * FROM " + HelpRequestConstant.TABLE_NAME + " WHERE " + HelpRequestConstant.COLUMN_IS_UPLOADED_TEACHER + " =:isUploadedTeacher")
    List<HelpRequest> getTeacherRequestRecords(boolean isUploadedTeacher);

    @Query("SELECT * FROM " + HelpRequestConstant.TABLE_NAME + " WHERE " + HelpRequestConstant.COLUMN_IS_UPLOADED_SUPERVISOR + " =:isUploadedSupervisor")
    List<HelpRequest> getSupervisorRequestRecords(boolean isUploadedSupervisor);
}
