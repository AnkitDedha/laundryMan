package com.laundryman.laundrymanager.service.impl;

import com.laundryman.laundrymanager.model.ServiceType;
import com.laundryman.laundrymanager.repository.ServiceTypeRepository;
import com.laundryman.laundrymanager.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    @Autowired
    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    public ServiceType saveServiceType(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public Optional<ServiceType> getServiceTypeById(Long id) {
        return serviceTypeRepository.findById(id);
    }

    @Override
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }

    @Override
    public ServiceType updateServiceType(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public void deleteServiceType(Long id) {
        serviceTypeRepository.deleteById(id);
    }
}
