package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.InventoryItem;
import com.laundryman.laundrymanager.repository.InventoryItemRepository;
import com.laundryman.laundrymanager.service.impl.InventoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    @Mock
    private InventoryItemRepository inventoryItemRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveInventoryItem() {
        InventoryItem item = new InventoryItem();
        item.setName("Detergent");
        when(inventoryItemRepository.save(any(InventoryItem.class))).thenReturn(item);

        InventoryItem savedItem = inventoryService.saveInventoryItem(item);
        assertNotNull(savedItem);
        assertEquals("Detergent", savedItem.getName());
    }

    @Test
    void getInventoryItemById() {
        InventoryItem item = new InventoryItem();
        item.setId(1L);
        when(inventoryItemRepository.findById(1L)).thenReturn(Optional.of(item));

        Optional<InventoryItem> foundItem = inventoryService.getInventoryItemById(1L);
        assertTrue(foundItem.isPresent());
        assertEquals(1L, foundItem.get().getId());
    }

    @Test
    void getAllInventoryItems() {
        when(inventoryItemRepository.findAll()).thenReturn(Arrays.asList(new InventoryItem(), new InventoryItem()));

        assertEquals(2, inventoryService.getAllInventoryItems().size());
    }

    @Test
    void updateInventoryItem() {
        InventoryItem item = new InventoryItem();
        item.setId(1L);
        item.setName("Updated Item");
        when(inventoryItemRepository.save(any(InventoryItem.class))).thenReturn(item);

        InventoryItem updatedItem = inventoryService.updateInventoryItem(item);
        assertNotNull(updatedItem);
        assertEquals("Updated Item", updatedItem.getName());
    }

    @Test
    void deleteInventoryItem() {
        doNothing().when(inventoryItemRepository).deleteById(1L);

        inventoryService.deleteInventoryItem(1L);
        verify(inventoryItemRepository, times(1)).deleteById(1L);
    }
}
