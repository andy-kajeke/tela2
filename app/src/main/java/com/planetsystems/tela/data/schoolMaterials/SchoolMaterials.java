package com.planetsystems.tela.data.schoolMaterials;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SchoolMaterialsConstants.TABLE_NAME)
public class SchoolMaterials {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SchoolMaterialsConstants.COLUMN_PRIMARY_KEY)
    private int primaryKey;

    @ColumnInfo(name = SchoolMaterialsConstants.COLUMN_TYPE)
    private String type;

    @ColumnInfo(name = SchoolMaterialsConstants.COLUMN_ID)
    private String id;

    @ColumnInfo(name = SchoolMaterialsConstants.COLUMN_CODE)
    private String code;

    @ColumnInfo(name = SchoolMaterialsConstants.COLUMN_ITEM_NAME)
    private String itemName;

    public SchoolMaterials(String type, String id, String code, String itemName) {
        this.type = type;
        this.id = id;
        this.code = code;
        this.itemName = itemName;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
