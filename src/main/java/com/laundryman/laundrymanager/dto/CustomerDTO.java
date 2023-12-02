package com.laundryman.laundrymanager.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    // Additional fields can be added based on what you need to expose in the API
}
