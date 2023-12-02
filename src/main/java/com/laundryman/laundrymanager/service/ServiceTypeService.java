package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.ServiceType;

import java.util.List;
import java.util.Optional;

public interface ServiceTypeService {
    ServiceType saveServiceType(ServiceType serviceType);
    Optional<ServiceType> getServiceTypeById(Long id);
    List<ServiceType> getAllServiceTypes();
    ServiceType updateServiceType(ServiceType serviceType);
    void deleteServiceType(Long id);
}
