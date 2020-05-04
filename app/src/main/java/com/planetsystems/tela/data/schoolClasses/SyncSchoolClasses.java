package com.planetsystems.tela.data.schoolClasses;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.data.Teacher.SyncTeacherTableConstants;

@Entity(tableName = SyncSchoolClassesConstants.TABLE_NAME)
public class SyncSchoolClasses {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SyncSchoolClassesConstants.PRIMARY_KEY)
    private int primaryKey;

    @ColumnInfo(name = SyncSchoolClassesConstants.COLUMN_ID)
    private String id;

    @ColumnInfo(name = SyncSchoolClassesConstants.COLUMN_CLASS_NAME)
    private String className;

    @ColumnInfo(name = SyncSchoolClassesConstants.COLUMN_CODE)
    private String code;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SyncSchoolClasses(String id, String className, String code) {
        this.id = id;
        this.className = className;
        this.code = code;
    }

    @Override
    public String toString() {
        return "SyncTimeTable{" +
                "primaryKey=" + primaryKey +
                ", className='" + className + '\'' +
                ", code='" + code + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
