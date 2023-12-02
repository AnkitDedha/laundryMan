package com.laundryman.laundrymanager.repository;

import com.laundryman.laundrymanager.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
    // Additional custom methods can be defined here
}
