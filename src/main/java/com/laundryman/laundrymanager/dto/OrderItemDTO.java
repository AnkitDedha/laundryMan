package com.laundryman.laundrymanager.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private String serviceType;
    private String description;
    private int quantity;
    private double price;
    // Additional fields as needed
}
