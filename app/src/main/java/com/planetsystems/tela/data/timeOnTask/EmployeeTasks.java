package com.planetsystems.tela.data.timeOnTask;

import com.planetsystems.tela.activities.staff.regularStaff.home.Tasks;

import java.util.List;

public class EmployeeTasks {
    String employeeNumber;
    String employeeId;
    List<Tasks> tasks;

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

}
