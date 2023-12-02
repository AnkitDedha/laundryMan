package com.laundryman.laundrymanager.repository;

import com.laundryman.laundrymanager.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Additional custom methods can be defined here
}
