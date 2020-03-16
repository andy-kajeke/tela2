package com.planetsystems.tela.enties;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "EmployeeRoles")
public class EmployeeRoles {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "dateCreated")
    private String dateCreated;

    @ColumnInfo(name = "dateUpdated")
    private String dateUpdated;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "employeeRole")
    private String employeeRole;

    @ColumnInfo(name = "status")
    private String status;
}
