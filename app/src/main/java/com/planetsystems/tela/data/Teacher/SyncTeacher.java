package com.planetsystems.tela.data.Teacher;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;


@Entity(tableName = SyncTeacherTableConstants.TABLE_NAME)
public class SyncTeacher {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SyncTeacherTableConstants.PRIMARY_KEY)
    private int primaryKey;

    @ColumnInfo(name = SyncTeacherTableConstants.ID_COLUMN_NAME)
    private String id;

    @ColumnInfo(name = SyncTeacherTableConstants.EMPLOYEE_ID_COLUMN_NAME)
    private String employeeId;

    @ColumnInfo(name = SyncTeacherTableConstants.MPS_COMPUTER_NUMBER_COLUMN_NAME)
    private String MPSComputerNumber;

    @ColumnInfo(name = SyncTeacherTableConstants.EMPLOYEE_NUMBER_COLUMN_NAME)
    private String employeeNumber;

    @ColumnInfo(name = SyncTeacherTableConstants.ROLE_COLUMN_NAME)
    private String role;

    @ColumnInfo(name = SyncTeacherTableConstants.DOD_COLUMN_NAME)
    private String dob;

    @ColumnInfo(name = SyncTeacherTableConstants.EMAIL_ADDRESS_COLUMN_NAME)
    private String emailAddress;

    @ColumnInfo(name = SyncTeacherTableConstants.FINGER_PRINT_COLUMN_NAME)
    private byte[] fingerPrint;

    @ColumnInfo(name = SyncTeacherTableConstants.FINGER_IMAGE_COLUMN_NAME)
    private Bitmap fingerImage;

    @ColumnInfo(name = SyncTeacherTableConstants.FIRST_NAME_COLUMN_NAME)
    private String firstName;

    @ColumnInfo(name = SyncTeacherTableConstants.LAST_NAME_COLUMN_NAME)
    private String lastName;

    @ColumnInfo(name = SyncTeacherTableConstants.GENDER_COLUMN_NAME)
    private String gender;

    @ColumnInfo(name = SyncTeacherTableConstants.INITIALS_COLUMN_NAME)
    private String initials;

    @ColumnInfo(name = SyncTeacherTableConstants.LICENSED_COLUMN_NAME)
    private boolean licensed;

    @ColumnInfo(name = SyncTeacherTableConstants.NATIONAL_ID_COLUMN_NAME)
    private String nationalId;

    @ColumnInfo(name = SyncTeacherTableConstants.PHONE_NUMBER_COLUMN_NAME)
    private String phoneNumber;

    @ColumnInfo(name = SyncTeacherTableConstants.SCHOOL_ID_COLUMN_NAME)
    private String schoolId;

    @ColumnInfo(name = SyncTeacherTableConstants.FINGER_PRINT_LENGTH)
    private int fingerPrintLength;

    @ColumnInfo(name = SyncTeacherTableConstants.IS_STORED_LOCALLY)
    private boolean isStoredLocally;

    public SyncTeacher(String id, String employeeId, String MPSComputerNumber, String employeeNumber, String role, String dob, String emailAddress, byte[] fingerPrint, Bitmap fingerImage, String firstName, String lastName, String gender, String initials, boolean licensed, String nationalId, String phoneNumber, String schoolId, int fingerPrintLength, boolean isStoredLocally) {
        this.id = id;
        this.employeeId = employeeId;
        this.MPSComputerNumber = MPSComputerNumber;
        this.employeeNumber = employeeNumber;
        this.role = role;
        this.dob = dob;
        this.emailAddress = emailAddress;
        this.fingerPrint = fingerPrint;
        this.fingerImage = fingerImage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.initials = initials;
        this.licensed = licensed;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.schoolId = schoolId;
        this.fingerPrintLength = fingerPrintLength;
        this.isStoredLocally = isStoredLocally;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public byte[] getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(byte[] fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getFingerImage() {
        return fingerImage;
    }

    public void setFingerImage(String fingerImage) {
        this.fingerImage = fingerImage;
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

    public int getFingerPrintLength() {
        return fingerPrintLength;
    }

    public void setFingerPrintLength(int fingerPrintLength) {
        this.fingerPrintLength = fingerPrintLength;
    }

    public boolean isStoredLocally() {
        return isStoredLocally;
    }

    public void setStoredLocally(boolean storedLocally) {
        isStoredLocally = storedLocally;
    }

    @Override
    public String toString() {
        return "SyncTeacher{" +
                "primaryKey=" + primaryKey +
                ", id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", MPSComputerNumber='" + MPSComputerNumber + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", role='" + role + '\'' +
                ", dob='" + dob + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", fingerPrint=" + Arrays.toString(fingerPrint) +
                ", fingerImage='" + fingerImage + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", initials='" + initials + '\'' +
                ", licensed=" + licensed +
                ", nationalId='" + nationalId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", fingerPrintLength=" + fingerPrintLength +
                ", isStoredLocally=" + isStoredLocally +
                '}';
    }
}