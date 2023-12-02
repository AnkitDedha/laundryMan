package com.laundryman.laundrymanager.controller;

import com.laundryman.laundrymanager.dto.CustomerDTO;
import com.laundryman.laundrymanager.model.Customer;
import com.laundryman.laundrymanager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody Customer customer) {
//        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerService.saveCustomer(customer);
        CustomerDTO savedCustomerDTO = convertToDTO(savedCustomer);
        return ResponseEntity.ok().body(savedCustomerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customerDTOs = customerService.getAllCustomers().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(customerDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer customerToUpdate = convertToEntity(customerDTO);
        customerToUpdate.setId(id); // Ensure the ID is set for the update
        Customer updatedCustomer = customerService.updateCustomer(customerToUpdate);
        CustomerDTO updatedCustomerDTO = convertToDTO(updatedCustomer);
        return ResponseEntity.ok().body(updatedCustomerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    // Utility method to convert DTO to entity
    private Customer convertToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        // Map fields from CustomerDTO to Customer
        customer.setName(dto.getName());
        // Add other field mappings as necessary
        return customer;
    }

    // Utility method to convert entity to DTO
    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        // Map fields from Customer to CustomerDTO
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        // Add other field mappings as necessary
        return dto;
    }
}
