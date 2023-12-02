package com.laundryman.laundrymanager.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScheduleDTO {
    private Long id;
    private LocalDateTime scheduledTime;
    private String type; // e.g., pickup, delivery
    private Long orderId; // Reference to the order
    // Include additional details as required
}
