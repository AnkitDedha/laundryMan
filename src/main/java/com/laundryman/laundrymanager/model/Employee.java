package com.laundryman.laundrymanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {

    private final GenerationType generationType = GenerationType.IDENTITY;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName; // The first name of the employee

    @Column(name = "last_name")
    private String lastName; // The last name of the employee

    @Column(name = "email", unique = true)
    private String email; // The email address of the employee

    @Column(name = "phone_number")
    private String phoneNumber; // The phone number of the employee

    @Column(name = "hire_date")
    private LocalDate hireDate; // The date when the employee was hired

    @Column(name = "position")
    private String position; // The position or role of the employee within the company

    // Additional fields can be added as per requirements

    // Lombok annotations manage constructors, getters, and setters
}
