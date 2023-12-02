package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.Order;
import com.laundryman.laundrymanager.repository.OrderRepository;
import com.laundryman.laundrymanager.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrder() {
        Order order = new Order();
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.saveOrder(order);
        assertNotNull(savedOrder);
    }

    @Test
    void getOrderById() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.getOrderById(1L);
        assertTrue(foundOrder.isPresent());
        assertEquals(1L, foundOrder.get().getId());
    }

    @Test
    void getAllOrders() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(new Order(), new Order()));

        assertEquals(2, orderService.getAllOrders().size());
    }

    @Test
    void updateOrder() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order updatedOrder = orderService.updateOrder(order);
        assertNotNull(updatedOrder);
        assertEquals(1L, updatedOrder.getId());
    }

    @Test
    void deleteOrder() {
        doNothing().when(orderRepository).deleteById(1L);

        orderService.deleteOrder(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }
}
