package com.planetsystems.tela.data.schoolMaterialRequests;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SchoolMaterialRequestsDao {
    @Insert
    void insertMaterialRequest(SchoolMaterialRequests schoolMaterialRequests);

    @Query("SELECT * FROM " + SchoolMaterialRequestsConstants.TABLE_NAME)
    LiveData<List<SchoolMaterialRequests>> getAllMaterialRequest();

    @Update
    void update(SchoolMaterialRequests schoolMaterialRequests);

    @Query("SELECT * FROM " + SchoolMaterialRequestsConstants.TABLE_NAME +
            " WHERE "
            + SchoolMaterialRequestsConstants.APPROVAL_STATUS +
            " =:approvalStatus "
    )
    LiveData<List<SchoolMaterialRequests>> getRequestByApprovalStatus (String approvalStatus);

    @Query("SELECT * FROM " + SchoolMaterialRequestsConstants.TABLE_NAME +
            " WHERE "
            + SchoolMaterialRequestsConstants.EMPLOYEE_NO +
            " =:employeeNo "
    )
    LiveData<List<SchoolMaterialRequests>> getRequestByEmployeeNo (String employeeNo);

    @Query("UPDATE " + SchoolMaterialRequestsConstants.TABLE_NAME
            + " SET "
            + SchoolMaterialRequestsConstants.APPROVAL_STATUS + " =:approvalStatus"
            + ","
            + SchoolMaterialRequestsConstants.APPROVAL_DATE + " =:approvalDate"
            + ","
            + SchoolMaterialRequestsConstants.SUPERVISOR_ID + " =:supervisorID"
            + " WHERE "
            + SchoolMaterialRequestsConstants.PRIMARY_KEY + " =:primaryKey")
    void update(String approvalStatus, String approvalDate,String supervisorID, int primaryKey);

    @Query("SELECT * FROM " + SchoolMaterialRequestsConstants.TABLE_NAME + " WHERE " + SchoolMaterialRequestsConstants.IS_UPLOADED_TEACHER + " =:isUploadedTeacher")
    List<SchoolMaterialRequests> getTeacherRequestRecords(boolean isUploadedTeacher);

    @Query("SELECT * FROM " + SchoolMaterialRequestsConstants.TABLE_NAME + " WHERE " + SchoolMaterialRequestsConstants.IS_UPLOADED_SUPERVISOR + " =:isUploadedSupervisor")
    List<SchoolMaterialRequests> getSupervisorRequestRecords(boolean isUploadedSupervisor);
}
