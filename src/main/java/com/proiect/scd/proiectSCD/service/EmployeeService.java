package com.proiect.scd.proiectSCD.service;

import com.proiect.scd.proiectSCD.dtos.EmployeeDTO;
import com.proiect.scd.proiectSCD.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    boolean deleteEmployeeById(Long id);

    List<Employee> getAllEmployeesPerDepartment(String department);

    List<Employee> getAllManagersPerDepartment(String department);

    Employee mapDTOtoEntity(EmployeeDTO employeeDTO);

}
