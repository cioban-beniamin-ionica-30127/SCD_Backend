package com.proiect.scd.proiectSCD.dtos;

import lombok.Data;

@Data
public class EmployeeDTO {
    private String name;
    private String email;
    private Long managerId;
    private Long departmentId;
}
