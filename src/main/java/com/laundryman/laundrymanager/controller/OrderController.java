package com.laundryman.laundrymanager.controller;

import com.laundryman.laundrymanager.dto.OrderDTO;
import com.laundryman.laundrymanager.model.Order;
import com.laundryman.laundrymanager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderService.saveOrder(order);
        OrderDTO savedOrderDTO = convertToDTO(savedOrder);
        return ResponseEntity.ok().body(savedOrderDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orderDTOs = orderService.getAllOrders().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(orderDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order orderToUpdate = convertToEntity(orderDTO);
        orderToUpdate.setId(id); // Ensure the ID is set for the update
        Order updatedOrder = orderService.updateOrder(orderToUpdate);
        OrderDTO updatedOrderDTO = convertToDTO(updatedOrder);
        return ResponseEntity.ok().body(updatedOrderDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

    // Utility method to convert DTO to entity
    private Order convertToEntity(OrderDTO dto) {
        Order order = new Order();

        // Map fields from OrderDTO to Order
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
        order.setDescription(dto.getDescription());
        // Add other field mappings as necessary

        return order;
    }

    // Utility method to convert entity to DTO
    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();

        // Map fields from Order to OrderDTO
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setDescription(order.getDescription());

        // Add other field mappings as necessary

        return dto;
    }
}
