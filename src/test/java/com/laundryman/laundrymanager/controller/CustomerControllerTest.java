package com.laundryman.laundrymanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundryman.laundrymanager.dto.CustomerDTO;
import com.laundryman.laundrymanager.model.Customer;
import com.laundryman.laundrymanager.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Setup necessary mock behavior here
    }

    @Test
    void createCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getCustomerById() throws Exception {
        Customer mockCustomer = new Customer();
        mockCustomer.setId(1L); // Set additional fields as required
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(mockCustomer));

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getAllCustomers() throws Exception {
        List<Customer> mockCustomers = Arrays.asList(new Customer(), new Customer());
        when(customerService.getAllCustomers()).thenReturn(mockCustomers);

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void updateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);

        mockMvc.perform(put("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/customers/1"))
                .andExpect(status().isOk());
    }
}
