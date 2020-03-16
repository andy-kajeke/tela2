package com.planetsystems.tela.dao;


import androidx.room.Dao;
import androidx.room.Insert;

import com.planetsystems.tela.enties.EmployeeRole;

@Dao
public interface EmployeeRoleDao {

    @Insert
    void insertEmployeeRole(EmployeeRole employeeRole);

}
