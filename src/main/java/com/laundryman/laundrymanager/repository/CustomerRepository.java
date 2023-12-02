package com.laundryman.laundrymanager.repository;

import com.laundryman.laundrymanager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional custom methods can be defined here
}
