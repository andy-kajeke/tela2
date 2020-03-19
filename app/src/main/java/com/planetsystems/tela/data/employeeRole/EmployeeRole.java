package com.planetsystems.tela.data.employeeRole;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = EmployeeRoleConstants.TABLE_NAME)
public class EmployeeRole {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = EmployeeRoleConstants.ID)
    private String id;

    @ColumnInfo(name = EmployeeRoleConstants.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = EmployeeRoleConstants.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = EmployeeRoleConstants.DESCRIPTION)
    private String description;

    @ColumnInfo(name = EmployeeRoleConstants.EMPLOYEE_ROLE)
    private String employeeRole;

    @ColumnInfo(name = "status")
    private String status;

    public EmployeeRole(String id, String dateCreated, String dateUpdated, String description, String employeeRole, String status) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.description = description;
        this.employeeRole = employeeRole;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return "Teacher Role " + this.id;
    }
}
