package com.laundryman.laundrymanager.repository;

import com.laundryman.laundrymanager.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Additional custom methods can be defined here
}
