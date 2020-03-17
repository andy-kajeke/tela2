package com.planetsystems.tela.data.helprequest;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = HelpRequestConstant.TABLE_NAME)
public class HelpRequest {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = HelpRequestConstant.ID)
    private String id;

    @ColumnInfo(name = HelpRequestConstant.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = HelpRequestConstant.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = HelpRequestConstant.STATUS)
    private String status;

    @ColumnInfo(name = HelpRequestConstant.APPROVED_STATUS)
    private String approvalStatus;

    @ColumnInfo(name = HelpRequestConstant.CONFIRMATION)
    private String confirmation;

    @ColumnInfo(name = HelpRequestConstant.COMMENT)
    private String comment;

    @ColumnInfo(name = HelpRequestConstant.DEPLOYMENT_SITE)
    private String deploymentSite;

    @ColumnInfo(name = HelpRequestConstant.DEPLOYMENT_SITE_ID)
    private String deploymentSiteId;

    @ColumnInfo(name = HelpRequestConstant.HELP_CATEGORY)
    private String helpCategory;

    @ColumnInfo(name = HelpRequestConstant.PRIORITY)
    private String priority;

    @ColumnInfo(name = HelpRequestConstant.STAFF_CODE)
    private String staffCode;

    @ColumnInfo(name = HelpRequestConstant.ROW_VER)
    private String rowVer;

    @ColumnInfo(name = HelpRequestConstant.ROW_ID)
    private String rowId;

    @ColumnInfo(name = HelpRequestConstant.REQUESTED_DATE)
    private String requestDate;

    @ColumnInfo(name = HelpRequestConstant.APPROVAL_DATE)
    private String approvalDate;
}
