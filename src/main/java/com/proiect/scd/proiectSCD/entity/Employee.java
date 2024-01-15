package com.proiect.scd.proiectSCD.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@Table (name = "employees")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnoreProperties("manager") // Ignore cyclic references for the 'manager' property
    private Employee manager;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnoreProperties("employees") // Add this line to ignore cyclic references
    private Department department;

}
