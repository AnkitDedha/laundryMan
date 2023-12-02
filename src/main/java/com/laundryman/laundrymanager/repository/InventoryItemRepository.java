package com.laundryman.laundrymanager.repository;

import com.laundryman.laundrymanager.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    // Additional custom methods can be defined here
}
