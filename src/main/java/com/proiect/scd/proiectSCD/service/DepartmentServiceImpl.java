package com.proiect.scd.proiectSCD.service;

import com.proiect.scd.proiectSCD.dtos.DepartmentDTO;
import com.proiect.scd.proiectSCD.dtos.DepartmentVO;
import com.proiect.scd.proiectSCD.entity.Department;
import com.proiect.scd.proiectSCD.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;


@Service
@SuppressWarnings("unused")
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department findDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Department findDepartmentByName(String departmentName) {
        return departmentRepository.findByName(departmentName);
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public boolean deleteDepartmentById(Long id) {
        if (findDepartmentById(id) != null){
            // stergerea subdepartamentelor
            deleteSubdepartmentsIfTheyExist(id);
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void deleteSubdepartmentsIfTheyExist(Long parentId) {
        List<Department> subdepartmentsList = departmentRepository.findByParentDepartmentId(parentId);
        for (Department subdepartement: subdepartmentsList) {
            departmentRepository.deleteById(subdepartement.getId());
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department mapDTOtoDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());

        if (departmentDTO.getParentDepartmentId() > 0) {
            Department parent = departmentRepository.findById(departmentDTO.getParentDepartmentId()).orElse(null);
            department.setParentDepartment(parent);
        }

        return department;
    }

    @Override
    public List<Department> getAllChildDepartmentsOfDepartment(DepartmentVO departmentVO) {
        List<Department> allDepartments =  departmentRepository.findAll();
        return allDepartments.stream()
                .filter(department -> department.getParentDepartment() != null)
                .filter(department -> Objects.equals(department.getParentDepartment().getId(), departmentVO.getId()))
                .toList();
    }
}
