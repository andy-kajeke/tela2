package com.planetsystems.tela.enties;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.SyncTableConstants;

@Entity(tableName = SyncTableConstants.SyncTeachers)
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
    //@NonNull
    private String fingerPrint;


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

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getMPSComputerNumber() {
        return MPSComputerNumber;
    }

    public void setMPSComputerNumber(String MPSComputerNumber) {
        this.MPSComputerNumber = MPSComputerNumber;
    }

    @NonNull
    public String getDob() {
        return dob;
    }

    public void setDob(@NonNull String dob) {
        this.dob = dob;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @NonNull
    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint( String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getGender() {
        return gender;
    }

    public void setGender(@NonNull String gender) {
        this.gender = gender;
    }

    @NonNull
    public String getInitials() {
        return initials;
    }

    public void setInitials(@NonNull String initials) {
        this.initials = initials;
    }

    public int getLicensed() {
        return licensed;
    }

    public void setLicensed(int licensed) {
        this.licensed = licensed;
    }

    @NonNull
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(@NonNull String nationalId) {
        this.nationalId = nationalId;
    }

    @NonNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(@NonNull String schoolId) {
        this.schoolId = schoolId;
    }

    public SyncTeacher(@NonNull String id, String MPSComputerNumber, @NonNull String dob, String emailAddress, String fingerPrint, @NonNull String firstName, @NonNull String lastName, @NonNull String gender, @NonNull String initials, int licensed, @NonNull String nationalId, @NonNull String phoneNumber, @NonNull String schoolId) {
        this.id = id;
        this.MPSComputerNumber = MPSComputerNumber;
        this.dob = dob;
        this.emailAddress = emailAddress;
        this.fingerPrint = fingerPrint;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.initials = initials;
        this.licensed = licensed;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.schoolId = schoolId;
    }
}
