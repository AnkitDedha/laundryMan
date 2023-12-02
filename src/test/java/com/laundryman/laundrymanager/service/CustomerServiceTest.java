package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.Customer;
import com.laundryman.laundrymanager.repository.CustomerRepository;
import com.laundryman.laundrymanager.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.saveCustomer(customer);
        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.getCustomerById(1L);
        assertTrue(foundCustomer.isPresent());
        assertEquals(1L, foundCustomer.get().getId());
    }

    @Test
    void getAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(new Customer(), new Customer()));

        assertEquals(2, customerService.getAllCustomers().size());
    }

    @Test
    void updateCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Updated Name");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer updatedCustomer = customerService.updateCustomer(customer);
        assertNotNull(updatedCustomer);
        assertEquals("Updated Name", updatedCustomer.getName());
    }

    @Test
    void deleteCustomer() {
        doNothing().when(customerRepository).deleteById(1L);

        customerService.deleteCustomer(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }
}
