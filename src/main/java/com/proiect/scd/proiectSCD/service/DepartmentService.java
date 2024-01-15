package com.proiect.scd.proiectSCD.service;

import com.proiect.scd.proiectSCD.dtos.DepartmentDTO;
import com.proiect.scd.proiectSCD.dtos.DepartmentVO;
import com.proiect.scd.proiectSCD.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department findDepartmentById(Long id);
    Department findDepartmentByName(String departmentName);

    Department saveDepartment(Department department);

    boolean deleteDepartmentById (Long id);

    List<Department> getAllDepartments();

    Department mapDTOtoDepartment(DepartmentDTO departmentDTO);

    List<Department> getAllChildDepartmentsOfDepartment(DepartmentVO departmentVO);
}
