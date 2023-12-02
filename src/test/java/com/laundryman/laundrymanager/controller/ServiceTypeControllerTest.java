package com.laundryman.laundrymanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundryman.laundrymanager.dto.ServiceTypeDTO;
import com.laundryman.laundrymanager.model.ServiceType;
import com.laundryman.laundrymanager.service.ServiceTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ServiceTypeController.class)
class ServiceTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceTypeService serviceTypeService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Set up necessary mock behavior here
    }

    @Test
    void createServiceType() throws Exception {
        ServiceTypeDTO serviceTypeDTO = new ServiceTypeDTO();
        serviceTypeDTO.setId(1L); // Set additional fields as required

        mockMvc.perform(post("/api/service-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(serviceTypeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getServiceTypeById() throws Exception {
        ServiceType mockServiceType = new ServiceType();
        mockServiceType.setId(1L); // Set other required fields of mockServiceType
        when(serviceTypeService.getServiceTypeById(1L)).thenReturn(Optional.of(mockServiceType));

        ServiceTypeDTO serviceTypeDTO = convertToServiceTypeDTO(mockServiceType); // Assume this conversion logic exists
        mockMvc.perform(get("/api/service-types/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(serviceTypeDTO.getId()));
    }

    @Test
    void getAllServiceTypes() throws Exception {
        when(serviceTypeService.getAllServiceTypes()).thenReturn(Arrays.asList(new ServiceType(), new ServiceType())); // Assuming conversion in service

        mockMvc.perform(get("/api/service-types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    // Utility method to convert ServiceType to ServiceTypeDTO
    private ServiceTypeDTO convertToServiceTypeDTO(ServiceType serviceType) {
        ServiceTypeDTO dto = new ServiceTypeDTO();
        dto.setId(serviceType.getId());
        // Map other fields from ServiceType to ServiceTypeDTO as per your class structure
        return dto;
    }
}
