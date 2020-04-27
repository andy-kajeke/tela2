package com.planetsystems.tela.data.Teacher;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = SyncTeacherTableConstants.TABLE_NAME)
public class SyncTeacher {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SyncTeacherTableConstants.PRIMARY_KEY)
    private int id;

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
    private String fingerPrint;

    @ColumnInfo(name = SyncTeacherTableConstants.FINGER_IMAGE_COLUMN_NAME)
    private String fingerImage;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getFingerImage() {
        return fingerImage;
    }

    public void setFingerImage(String fingerImage) {
        this.fingerImage = fingerImage;
    }

    public SyncTeacher(String employeeId, String MPSComputerNumber, String employeeNumber, String role, String dob, String emailAddress, String fingerPrint, String fingerImage, String firstName, String lastName, String gender, String initials, boolean licensed, String nationalId, String phoneNumber, String schoolId) {
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
    }

    private SyncTeacher() {
    }

    public static class Builder {
        private SyncTeacher INSTANCE;
        public Builder() {
            INSTANCE = new SyncTeacher();
        }

        public SyncTeacher setEmployeeId(String employeeID) {
            INSTANCE.setEmployeeId(employeeID);
            return INSTANCE;
        }

        public SyncTeacher setMPSComputerNumber(String computerNumber) {
            INSTANCE.setMPSComputerNumber(computerNumber);
            return INSTANCE;
        }

        public SyncTeacher setEmployeeNumber(String employeeNumber) {
            INSTANCE.setEmployeeNumber(employeeNumber);
            return INSTANCE;
        }

        public SyncTeacher setRole(String role) {
            INSTANCE.setRole(role);
            return INSTANCE;
        }

        public SyncTeacher setDOB(String dob) {
            INSTANCE.setDob(dob);
            return INSTANCE;
        }

        public SyncTeacher setEmailAddress(String emailAddress) {
            INSTANCE.setEmailAddress(emailAddress);
            return INSTANCE;
        }

        public SyncTeacher setFingerPrint(String fingerPrint) {
            INSTANCE.setFingerPrint(fingerPrint);
            return INSTANCE;
        }

        public SyncTeacher setFingerImage(String fingerImage) {
            INSTANCE.setFingerImage(fingerImage);
            return INSTANCE;
        }

        public SyncTeacher setFirstName(String firstName) {
            INSTANCE.setFirstName(firstName);
            return INSTANCE;
        }

        public SyncTeacher setLastName(String lastName) {
            INSTANCE.setLastName(lastName);
            return INSTANCE;
        }

        public SyncTeacher setGender(String gender) {
            INSTANCE.setGender(gender);
            return INSTANCE;
        }

        public SyncTeacher setInitials(String initials) {
            INSTANCE.setInitials(initials);
            return INSTANCE;
        }

        public SyncTeacher setLicensed(Boolean licensed) {
            INSTANCE.setLicensed(licensed);
            return INSTANCE;
        }

        public SyncTeacher setNationalID(String nationalID) {
            INSTANCE.setNationalId(nationalID);
            return INSTANCE;
        }

        public SyncTeacher setPhoneNumber(String phoneNumber) {
            INSTANCE.setPhoneNumber(phoneNumber);
            return INSTANCE;
        }

        public SyncTeacher setSchoolID(String schoolID) {
            INSTANCE.setSchoolId(schoolID);
            return INSTANCE;
        }
    }

    @Override
    public String toString() {
        return "SyncTeacher{" +
                "id=" + id +
                ", employeeId='" + employeeId + '\'' +
                ", MPSComputerNumber='" + MPSComputerNumber + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", role='" + role + '\'' +
                ", dob='" + dob + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", fingerPrint='" + fingerPrint + '\'' +
                ", fingerImage='" + fingerImage + '\'' +
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
}