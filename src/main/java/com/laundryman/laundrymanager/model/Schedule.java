package com.laundryman.laundrymanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scheduled_time")
    private LocalDateTime scheduledTime; // The time for the pickup/delivery

    @Column(name = "type")
    private String type; // Type of schedule (e.g., pickup, delivery)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // The order this schedule is associated with

    // Lombok annotations manage constructors, getters, and setters
}
