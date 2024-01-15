package com.proiect.scd.proiectSCD.repository;

import com.proiect.scd.proiectSCD.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentName( String departmentName);

    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName AND e.manager IS NOT NULL")
    List<Employee> findAllEmployeesInDepartment(String departmentName);

    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName AND e.manager IS NULL")
    List<Employee> findAllManagersInDepartment(String departmentName);

    @Query("SELECT e FROM Employee e WHERE e.manager.name = :managerName")
    List<Employee> findEmployeesOfManager(String managerName);
}
