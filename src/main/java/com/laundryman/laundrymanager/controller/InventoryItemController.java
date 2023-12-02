package com.laundryman.laundrymanager.controller;

import com.laundryman.laundrymanager.dto.InventoryItemDTO;
import com.laundryman.laundrymanager.model.InventoryItem;
import com.laundryman.laundrymanager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventory")
public class InventoryItemController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryItemController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<InventoryItemDTO> createInventoryItem(@RequestBody InventoryItemDTO inventoryItemDTO) {
        InventoryItem inventoryItem = convertToEntity(inventoryItemDTO);
        InventoryItem savedInventoryItem = inventoryService.saveInventoryItem(inventoryItem);
        InventoryItemDTO savedInventoryItemDTO = convertToDTO(savedInventoryItem);
        return ResponseEntity.ok().body(savedInventoryItemDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemDTO> getInventoryItemById(@PathVariable Long id) {
        return inventoryService.getInventoryItemById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<InventoryItemDTO>> getAllInventoryItems() {
        List<InventoryItemDTO> inventoryItemDTOs = inventoryService.getAllInventoryItems().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(inventoryItemDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryItemDTO> updateInventoryItem(@PathVariable Long id, @RequestBody InventoryItemDTO inventoryItemDTO) {
        InventoryItem inventoryItemToUpdate = convertToEntity(inventoryItemDTO);
        inventoryItemToUpdate.setId(id); // Ensure the ID is set for the update
        InventoryItem updatedInventoryItem = inventoryService.updateInventoryItem(inventoryItemToUpdate);
        InventoryItemDTO updatedInventoryItemDTO = convertToDTO(updatedInventoryItem);
        return ResponseEntity.ok().body(updatedInventoryItemDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
        return ResponseEntity.ok().build();
    }

    // Utility method to convert DTO to entity
    private InventoryItem convertToEntity(InventoryItemDTO dto) {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setName(dto.getName());
        inventoryItem.setDescription(dto.getDescription());
        inventoryItem.setQuantity(dto.getQuantity());
        inventoryItem.setReorderLevel(dto.getReorderLevel());
        return inventoryItem;
    }

    // Utility method to convert entity to DTO
    private InventoryItemDTO convertToDTO(InventoryItem inventoryItem) {
        InventoryItemDTO dto = new InventoryItemDTO();
        dto.setId(inventoryItem.getId());
        dto.setName(inventoryItem.getName());
        dto.setDescription(inventoryItem.getDescription());
        dto.setQuantity(inventoryItem.getQuantity());
        dto.setReorderLevel(inventoryItem.getReorderLevel());
        return dto;
    }
}
