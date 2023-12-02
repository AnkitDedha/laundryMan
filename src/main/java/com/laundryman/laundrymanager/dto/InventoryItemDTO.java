package com.laundryman.laundrymanager.dto;

import lombok.Data;

@Data
public class InventoryItemDTO {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private int reorderLevel;
    // Additional fields as needed
}
