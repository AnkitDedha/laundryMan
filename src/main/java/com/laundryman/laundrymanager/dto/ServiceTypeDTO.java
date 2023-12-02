package com.laundryman.laundrymanager.dto;

import lombok.Data;

@Data
public class ServiceTypeDTO {
    private Long id;
    private String name;
    private String description;
    private double rate;
    // Additional fields if necessary
}
