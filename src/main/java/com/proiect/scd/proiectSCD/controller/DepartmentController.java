package com.proiect.scd.proiectSCD.controller;

import com.proiect.scd.proiectSCD.dtos.DepartmentDTO;
import com.proiect.scd.proiectSCD.dtos.DepartmentVO;
import com.proiect.scd.proiectSCD.entity.Department;
import com.proiect.scd.proiectSCD.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/!#")
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    // TESTAT
    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments(){
        return ResponseEntity.ofNullable(departmentService.getAllDepartments());
    }

    // TESTAT
    @GetMapping("/allParentDepartments")
    public ResponseEntity<List<DepartmentVO>> getAllParentDepartments(){
        List<Department> departmentList = departmentService.getAllDepartments();
        List<Department> headDepartments = departmentList
                .stream()
                .filter(department -> department.getParentDepartment() == null)
                .toList();

        List<DepartmentVO> departmentVOS = DepartmentVO.convertDepartmentListToDepartmentVOList(headDepartments);
        for (DepartmentVO departmentVO: departmentVOS) {
            List<Department> childOfCurrentDepartment = departmentService.getAllChildDepartmentsOfDepartment(departmentVO);
            departmentVO.setChildDepartments(childOfCurrentDepartment);
        }

        return ResponseEntity.ofNullable(departmentVOS);
    }

    // TESTAT
    @GetMapping("/byId/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id){
        return ResponseEntity.ofNullable(departmentService.findDepartmentById(id));
    }

    // TESTAT
    @GetMapping("/byName/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable String name){
        return ResponseEntity.ofNullable(departmentService.findDepartmentByName(name));
    }

    // TESTAT
    @PostMapping("/{parentId}")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department, @PathVariable Long parentId){
        if (parentId != 0) {
            department.setParentDepartment(departmentService.findDepartmentById(parentId));
        }
        return ResponseEntity.ofNullable(departmentService.saveDepartment(department));
    }

    // TESTAT
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO){
        Department department = departmentService.mapDTOtoDepartment(departmentDTO);
        department.setId(id);
        return ResponseEntity.ofNullable(departmentService.saveDepartment(department));
    }

    // TESTAT
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
        boolean deletedSuccessfully = departmentService.deleteDepartmentById(id);
        if (deletedSuccessfully) {
            return new ResponseEntity<>("Successfully deleted department and related managers and employees!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Department id not found!", HttpStatus.BAD_REQUEST);
    }
}
