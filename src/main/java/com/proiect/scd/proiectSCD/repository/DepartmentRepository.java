package com.proiect.scd.proiectSCD.repository;

import com.proiect.scd.proiectSCD.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);
    List<Department> findByParentDepartmentId(Long parentDepartmentId);

}

