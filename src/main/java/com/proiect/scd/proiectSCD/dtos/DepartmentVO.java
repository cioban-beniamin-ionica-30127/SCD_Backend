package com.proiect.scd.proiectSCD.dtos;

import com.proiect.scd.proiectSCD.entity.Department;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DepartmentVO {
    private Long id;
    private String name;
    private String description;
    private List<Department> childDepartments;

    public static List<DepartmentVO> convertDepartmentListToDepartmentVOList (List<Department> departmentList) {
        List<DepartmentVO> departmentVOS = new ArrayList<>();
        for (Department department: departmentList) {
            DepartmentVO departmentVO = new DepartmentVO();
            departmentVO.setId(department.getId());
            departmentVO.setName(department.getName());
            departmentVO.setDescription(department.getDescription());
            departmentVO.setChildDepartments(new ArrayList<>());

            departmentVOS.add(departmentVO);
        }

        return departmentVOS;
    }
}
