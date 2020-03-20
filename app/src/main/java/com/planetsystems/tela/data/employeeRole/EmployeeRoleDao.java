package com.planetsystems.tela.data.employeeRole;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface EmployeeRoleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertEmployeeRole(EmployeeRole employeeRole);

    @Query("SELECT * from " + EmployeeRoleConstants.TABLE_NAME)
    LiveData<List<EmployeeRole>> queryAllEmployeeRoles();

    @Update(entity = EmployeeRole.class)
    void updateStudent(EmployeeRole employeeRole);

    @Delete(entity = EmployeeRole.class)
    void deleteStudent(EmployeeRole employeeRole);

}
