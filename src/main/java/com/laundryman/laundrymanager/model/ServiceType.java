package com.laundryman.laundrymanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "service_types")
@Data
@NoArgsConstructor
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; // Name of the service type

    @Column(name = "description")
    private String description; // Description of the service

    @Column(name = "rate")
    private double rate; // Rate charged for the service

    // Lombok annotations manage constructors, getters, and setters
}
