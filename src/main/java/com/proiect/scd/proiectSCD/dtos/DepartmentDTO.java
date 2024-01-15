package com.proiect.scd.proiectSCD.dtos;

import lombok.Data;

@Data
public class DepartmentDTO {
    private String name;
    private String description;
    private Long parentDepartmentId;
}
