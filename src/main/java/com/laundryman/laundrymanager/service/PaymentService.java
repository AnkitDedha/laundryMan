package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment savePayment(Payment payment);
    Optional<Payment> getPaymentById(Long id);
    List<Payment> getAllPayments();
    Payment updatePayment(Payment payment);
    void deletePayment(Long id);
}
