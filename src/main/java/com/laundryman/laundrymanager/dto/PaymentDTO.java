package com.laundryman.laundrymanager.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long id;
    private double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    // Include additional details as required
}
