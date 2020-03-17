package com.planetsystems.tela.data.employeeRole;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planetsystems.tela.data.employeeRole.EmployeeRoleConstants;

import java.util.List;

@Dao
public interface EmployeeRoleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertEmployeeRole(com.planetsystems.tela.enties.EmployeeRole employeeRole);

    @Query("SELECT * from " + EmployeeRoleConstants.TABLE_NAME)
    LiveData<List<com.planetsystems.tela.enties.EmployeeRole>> queryAllEmployeeRoles();

    @Update(entity = com.planetsystems.tela.enties.EmployeeRole.class)
    void updateStudent(com.planetsystems.tela.enties.EmployeeRole employeeRole);

    @Delete(entity = com.planetsystems.tela.enties.EmployeeRole.class)
    void deleteStudent(EmployeeRole employeeRole);

}
