package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Optional<Customer> getCustomerById(Long id);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Long id);
}
