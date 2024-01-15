package com.proiect.scd.proiectSCD.service;

import com.proiect.scd.proiectSCD.dtos.EmployeeDTO;
import com.proiect.scd.proiectSCD.entity.Employee;
import com.proiect.scd.proiectSCD.repository.DepartmentRepository;
import com.proiect.scd.proiectSCD.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@SuppressWarnings("unused")
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean deleteEmployeeById(Long id) {
        if (findEmployeeById(id) != null) {
            deleteReferenceForManagerEmployees(id);
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void deleteReferenceForManagerEmployees(Long id) {
        String managerName = employeeRepository.findById(id).orElse(null).getName();
        List<Employee> employees = employeeRepository.findEmployeesOfManager(managerName);
        for (Employee employee: employees) {
            employee.setManager(null);
            employeeRepository.save(employee);
        }
    }

    @Override
    public List<Employee> getAllEmployeesPerDepartment(String department) {
        return employeeRepository.findAllEmployeesInDepartment(department);
    }

    @Override
    public List<Employee> getAllManagersPerDepartment(String department) {
        return employeeRepository.findAllManagersInDepartment(department);
    }

    @Override
    public Employee mapDTOtoEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        if (employeeDTO.getManagerId() > 0) {
            employee.setManager(employeeRepository.findById(employeeDTO.getManagerId()).orElse(null));
        }
        if (employeeDTO.getDepartmentId() > 0) {
            employee.setDepartment(departmentRepository.findById(employeeDTO.getDepartmentId()).orElse(null));
        }

        return employee;
    }

}
