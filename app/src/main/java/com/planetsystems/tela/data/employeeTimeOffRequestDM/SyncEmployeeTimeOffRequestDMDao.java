package com.planetsystems.tela.data.employeeTimeOffRequestDM;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.constants.SyncTableConstants;

import java.util.List;

@Dao
public interface SyncEmployeeTimeOffRequestDMDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNewRequest(SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM);

    @Update
    void update(SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM);

    @Delete
    void delete(SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM);

    @Query("UPDATE " + SyncEmployeeTimeOffRequestDMsConstants.TABLE_NAME
            + " SET "
            + SyncEmployeeTimeOffRequestDMsConstants.COLUMN_APPROVAL_STATUS + " =:approvalStatus"
            + ","
            + SyncEmployeeTimeOffRequestDMsConstants.COLUMN_APPROVAL_DATE + " =:approvalDate"
            + " WHERE "
            + SyncEmployeeTimeOffRequestDMsConstants.COLUMN_PRIMARY_KEY + " =:primaryKey")
    void update(String approvalStatus,String approvalDate, int primaryKey);

    @Query("SELECT * FROM " + SyncEmployeeTimeOffRequestDMsConstants.TABLE_NAME)
    LiveData<List<SyncEmployeeTimeOffRequestDM>> getAllRecords();

    @Query("SELECT * FROM " + SyncEmployeeTimeOffRequestDMsConstants.TABLE_NAME +
            " WHERE "
            + SyncEmployeeTimeOffRequestDMsConstants.COLUMN_EMPLOYEE_REQUEST_TYPE +
            " =:requestType AND "
            + SyncEmployeeTimeOffRequestDMsConstants.COLUMN_APPROVAL_STATUS +
            " =:approvalStatus "
    )
    LiveData<List<SyncEmployeeTimeOffRequestDM>> getRequestTypeByApprovalStatus (String requestType, String approvalStatus);

    @Query("SELECT * FROM " + SyncEmployeeTimeOffRequestDMsConstants.TABLE_NAME +
            " WHERE "
            + SyncEmployeeTimeOffRequestDMsConstants.COLUMN_EMPLOYEE_REQUEST_TYPE +
            " =:requestType AND "
            + SyncEmployeeTimeOffRequestDMsConstants.COLUMN_EMPLOYEE_ID +
            " =:employeeNo "
    )
    LiveData<List<SyncEmployeeTimeOffRequestDM>> getApprovalStatusByEmployeeNo (String requestType, String employeeNo);

    @Query("SELECT * FROM " + SyncEmployeeTimeOffRequestDMsConstants.TABLE_NAME +
            " WHERE "
            + SyncEmployeeTimeOffRequestDMsConstants.COLUMN_APPROVAL_STATUS +
            " =:approvalStatus "
    )
    LiveData<List<SyncEmployeeTimeOffRequestDM>> getRequestByApprovalStatus (String approvalStatus);
}
