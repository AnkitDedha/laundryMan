package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order saveOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllOrders();
    Order updateOrder(Order order);
    void deleteOrder(Long id);
}
