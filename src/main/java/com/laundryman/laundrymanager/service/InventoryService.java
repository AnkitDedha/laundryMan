package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.InventoryItem;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    InventoryItem saveInventoryItem(InventoryItem item);
    Optional<InventoryItem> getInventoryItemById(Long id);
    List<InventoryItem> getAllInventoryItems();
    InventoryItem updateInventoryItem(InventoryItem item);
    void deleteInventoryItem(Long id);
}
