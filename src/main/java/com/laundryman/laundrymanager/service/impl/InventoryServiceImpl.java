package com.laundryman.laundrymanager.service.impl;

import com.laundryman.laundrymanager.model.InventoryItem;
import com.laundryman.laundrymanager.repository.InventoryItemRepository;
import com.laundryman.laundrymanager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryServiceImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public InventoryItem saveInventoryItem(InventoryItem item) {
        return inventoryItemRepository.save(item);
    }

    @Override
    public Optional<InventoryItem> getInventoryItemById(Long id) {
        return inventoryItemRepository.findById(id);
    }

    @Override
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    @Override
    public InventoryItem updateInventoryItem(InventoryItem item) {
        return inventoryItemRepository.save(item); // Assuming item has an ID for update
    }

    @Override
    public void deleteInventoryItem(Long id) {
        inventoryItemRepository.deleteById(id);
    }
}
