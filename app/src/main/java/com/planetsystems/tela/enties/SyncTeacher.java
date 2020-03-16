package com.planetsystems.tela.enties;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SyncTeacher")
public class SyncTeacher {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private String id;
    @ColumnInfo(name = "MPSComputerNumber")
    private String MPSComputerNumber;
    @ColumnInfo(name = "dob")
    @NonNull
    private String dob;
    @ColumnInfo(name = "emailAddress")
    private String emailAddress;
    @ColumnInfo(name = "fingerPrint")
    @NonNull
    private Byte[] fingerPrint;
    @ColumnInfo(name = "firstName")
    @NonNull
    private String firstName;
    @ColumnInfo(name = "lastName")
    @NonNull
    private String lastName;
    @ColumnInfo(name = "gender")
    @NonNull
    private String gender;
    @ColumnInfo(name = "initials")
    @NonNull
    private String initials;
    @ColumnInfo(name = "licensed")
    @NonNull
    private int licensed;
    @ColumnInfo(name = "nationalId")
    @NonNull
    private String nationalId;
    @ColumnInfo(name = "phoneNumber")
    @NonNull
    private String phoneNumber;
    @ColumnInfo(name = "schoolId")
    @NonNull
    private String schoolId;
}
