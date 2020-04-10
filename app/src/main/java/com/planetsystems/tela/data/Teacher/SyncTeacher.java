package com.planetsystems.tela.data.Teacher;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.SyncTableConstants;

import java.util.Arrays;

@Entity(tableName = SyncTableConstants.SyncTeachers)
public class SyncTeacher {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @ColumnInfo(name = "MPSComputerNumber")
    private String MPSComputerNumber;

    @ColumnInfo(name = "employeeNumber")
    private String employeeNumber;

    @ColumnInfo(name = "role")
    private String role;

    @ColumnInfo(name = "dob")
    private String dob;

    @ColumnInfo(name = "emailAddress")
    private String emailAddress;

    @ColumnInfo(name = "fingerPrint")
    private String fingerPrint;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "initials")
    private String initials;

    @ColumnInfo(name = "licensed")
    private boolean licensed;

    @ColumnInfo(name = "nationalId")
    private String nationalId;

    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;

    @ColumnInfo(name = "schoolId")
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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public boolean isLicensed() {
        return licensed;
    }

    public void setLicensed(boolean licensed) {
        this.licensed = licensed;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "SyncTeacher{" +
                "id='" + id + '\'' +
                ", MPSComputerNumber='" + MPSComputerNumber + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", role='" + role + '\'' +
                ", dob='" + dob + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", fingerPrint=" + fingerPrint +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", initials='" + initials + '\'' +
                ", licensed=" + licensed +
                ", nationalId='" + nationalId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", schoolId='" + schoolId + '\'' +
                '}';
    }

    public SyncTeacher(String MPSComputerNumber, String employeeNumber, String role, String dob, String emailAddress, String fingerPrint, String firstName, String lastName, String gender, String initials, boolean licensed, String nationalId, String phoneNumber, String schoolId) {
        this.MPSComputerNumber = MPSComputerNumber;
        this.employeeNumber = employeeNumber;
        this.role = role;
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