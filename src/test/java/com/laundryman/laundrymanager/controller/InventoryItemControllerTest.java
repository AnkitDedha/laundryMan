package com.laundryman.laundrymanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundryman.laundrymanager.dto.InventoryItemDTO;
import com.laundryman.laundrymanager.model.InventoryItem;
import com.laundryman.laundrymanager.service.InventoryService;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryItemController.class)
class InventoryItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Setup necessary mock behavior here
    }

    @Test
    void createInventoryItem() throws Exception {
        InventoryItemDTO inventoryItemDTO = new InventoryItemDTO();
        mockMvc.perform(post("/api/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventoryItemDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getInventoryItemById() throws Exception {
        InventoryItem mockInventoryItem = new InventoryItem();
        mockInventoryItem.setId(1L);
        // Set other required fields of mockInventoryItem
        when(inventoryService.getInventoryItemById(1L)).thenReturn(Optional.of(mockInventoryItem));

        InventoryItemDTO inventoryItemDTO = convertToInventoryItemDTO(mockInventoryItem); // Conversion logic
        mockMvc.perform(get("/api/inventory/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(inventoryItemDTO.getId()));
    }

    @Test
    void getAllInventoryItems() throws Exception {
        List<InventoryItem> mockInventoryItems = Arrays.asList(new InventoryItem(), new InventoryItem());
        // Set required fields of InventoryItem objects in mockInventoryItems list
        when(inventoryService.getAllInventoryItems()).thenReturn(mockInventoryItems);

        List<InventoryItemDTO> inventoryItemDTOs = mockInventoryItems.stream()
                .map(this::convertToInventoryItemDTO)
                .collect(Collectors.toList());
        mockMvc.perform(get("/api/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(inventoryItemDTOs.size()));
    }

    @Test
    void updateInventoryItem() throws Exception {
        InventoryItemDTO inventoryItemDTO = new InventoryItemDTO();
        inventoryItemDTO.setId(1L);

        mockMvc.perform(put("/api/inventory/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventoryItemDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteInventoryItem() throws Exception {
        mockMvc.perform(delete("/api/inventory/1"))
                .andExpect(status().isOk());
    }

    // Utility method to convert InventoryItem to InventoryItemDTO
    private InventoryItemDTO convertToInventoryItemDTO(InventoryItem item) {
        InventoryItemDTO dto = new InventoryItemDTO();
        dto.setId(item.getId());
        // Map other fields from InventoryItem to InventoryItemDTO as per your class structure
        return dto;
    }
}
