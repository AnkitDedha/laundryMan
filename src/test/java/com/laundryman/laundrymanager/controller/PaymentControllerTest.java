package com.laundryman.laundrymanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundryman.laundrymanager.dto.PaymentDTO;
import com.laundryman.laundrymanager.model.Payment;
import com.laundryman.laundrymanager.service.PaymentService;
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
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Set up necessary mock behavior here
    }

    @Test
    void createPayment() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(1L); // Set additional fields as required

        mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getPaymentById() throws Exception {
        Payment mockPayment = new Payment();
        mockPayment.setId(1L); // Set other required fields of mockPayment
        when(paymentService.getPaymentById(1L)).thenReturn(Optional.of(mockPayment));

        PaymentDTO paymentDTO = convertToPaymentDTO(mockPayment); // Assume this conversion logic exists
        mockMvc.perform(get("/api/payments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(paymentDTO.getId()));
    }

    @Test
    void getAllPayments() throws Exception {
        when(paymentService.getAllPayments()).thenReturn(Arrays.asList(new Payment(), new Payment())); // Assuming conversion in service

        mockMvc.perform(get("/api/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    // Utility method to convert Payment to PaymentDTO
    private PaymentDTO convertToPaymentDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        // Map other fields from Payment to PaymentDTO as per your class structure
        return dto;
    }
}
