package com.laundryman.laundrymanager.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private LocalDateTime orderDate;
    private String status;
    private String description; // Added description field
    private List<OrderItemDTO> orderItems; // Nested DTOs for order items
    private PaymentDTO payment; // Nested DTO for payment details
    // Additional fields as required
}