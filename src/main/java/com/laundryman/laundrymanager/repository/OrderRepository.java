package com.laundryman.laundrymanager.repository;

import com.laundryman.laundrymanager.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Additional custom methods can be defined here
}
