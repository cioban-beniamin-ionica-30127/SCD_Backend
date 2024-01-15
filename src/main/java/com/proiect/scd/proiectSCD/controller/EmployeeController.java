package com.proiect.scd.proiectSCD.controller;

import com.proiect.scd.proiectSCD.dtos.EmployeeDTO;
import com.proiect.scd.proiectSCD.entity.Employee;
import com.proiect.scd.proiectSCD.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    // TESTAT
    @GetMapping("/byDepartament/employees/{departmentName}")
    public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable String departmentName) {
        return ResponseEntity.ofNullable(employeeService.getAllEmployeesPerDepartment(departmentName));
    }

    // TESTAT
    @GetMapping("/byDepartament/managers/{departmentName}")
    public ResponseEntity<List<Employee>> getManagersByDepartment(@PathVariable String departmentName) {
        return ResponseEntity.ofNullable(employeeService.getAllManagersPerDepartment(departmentName));
    }

    // TESTAT
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ofNullable(employeeService.findEmployeeById(id));
    }

    // TESTAT
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.mapDTOtoEntity(employeeDTO);
        return ResponseEntity.ofNullable(employeeService.saveEmployee(employee));
    }

    // TESTAT
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.mapDTOtoEntity(employeeDTO);
        employee.setId(id);
        return ResponseEntity.ofNullable(employeeService.saveEmployee(employee));
    }

    // TESTAT
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        boolean deletedSuccessfully = employeeService.deleteEmployeeById(id);
        if (deletedSuccessfully) {
            return new ResponseEntity<>("Successfully deleted employee!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Employee id not found!", HttpStatus.BAD_REQUEST);
    }
}
