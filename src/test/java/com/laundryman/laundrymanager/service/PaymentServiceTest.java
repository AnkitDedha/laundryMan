package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.Payment;
import com.laundryman.laundrymanager.repository.PaymentRepository;
import com.laundryman.laundrymanager.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePayment() {
        Payment payment = new Payment();
        payment.setAmount(100.0);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment savedPayment = paymentService.savePayment(payment);
        assertNotNull(savedPayment);
        assertEquals(100.0, savedPayment.getAmount());
    }

    @Test
    void getPaymentById() {
        Payment payment = new Payment();
        payment.setId(1L);
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        Optional<Payment> foundPayment = paymentService.getPaymentById(1L);
        assertTrue(foundPayment.isPresent());
        assertEquals(1L, foundPayment.get().getId());
    }

    @Test
    void getAllPayments() {
        when(paymentRepository.findAll()).thenReturn(Arrays.asList(new Payment(), new Payment()));

        assertEquals(2, paymentService.getAllPayments().size());
    }

    @Test
    void updatePayment() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setAmount(150.0);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment updatedPayment = paymentService.updatePayment(payment);
        assertNotNull(updatedPayment);
        assertEquals(150.0, updatedPayment.getAmount());
    }

    @Test
    void deletePayment() {
        doNothing().when(paymentRepository).deleteById(1L);

        paymentService.deletePayment(1L);
        verify(paymentRepository, times(1)).deleteById(1L);
    }
}
