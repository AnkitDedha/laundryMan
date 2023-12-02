package com.laundryman.laundrymanager.controller;

import com.laundryman.laundrymanager.dto.PaymentDTO;
import com.laundryman.laundrymanager.model.Payment;
import com.laundryman.laundrymanager.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);
        Payment savedPayment = paymentService.savePayment(payment);
        PaymentDTO savedPaymentDTO = convertToDTO(savedPayment);
        return ResponseEntity.ok().body(savedPaymentDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> paymentDTOs = paymentService.getAllPayments().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(paymentDTOs);
    }

    // Utility method to convert DTO to entity
    private Payment convertToEntity(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMethod(dto.getPaymentMethod());
        // Set the associated order from DTO if necessary
        // payment.setOrder(convertToOrderEntity(dto.getOrder()));
        return payment;
    }

    // Utility method to convert entity to DTO
    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setPaymentMethod(payment.getPaymentMethod());
        // Convert and set the associated order to DTO if necessary
        // dto.setOrder(convertToOrderDTO(payment.getOrder()));
        return dto;
    }
}
