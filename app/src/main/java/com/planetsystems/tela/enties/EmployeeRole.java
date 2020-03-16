package com.planetsystems.tela.enties;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.EmployeeRoleName;

@Entity(tableName = EmployeeRoleName.TABLE_NAME)
public class EmployeeRole {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = EmployeeRoleName.ID)
    private String id;

    @ColumnInfo(name = EmployeeRoleName.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = EmployeeRoleName.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = EmployeeRoleName.DESCRIPTION)
    private String description;

    @ColumnInfo(name = EmployeeRoleName.EMPLOYEE_ROLE)
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
