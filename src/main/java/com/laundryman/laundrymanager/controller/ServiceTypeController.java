package com.laundryman.laundrymanager.controller;

import com.laundryman.laundrymanager.dto.ServiceTypeDTO;
import com.laundryman.laundrymanager.model.ServiceType;
import com.laundryman.laundrymanager.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/service-types")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    @Autowired
    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @PostMapping
    public ResponseEntity<ServiceTypeDTO> createServiceType(@RequestBody ServiceTypeDTO serviceTypeDTO) {
        ServiceType serviceType = convertToEntity(serviceTypeDTO);
        ServiceType savedServiceType = serviceTypeService.saveServiceType(serviceType);
        ServiceTypeDTO savedServiceTypeDTO = convertToDTO(savedServiceType);
        return ResponseEntity.ok().body(savedServiceTypeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeDTO> getServiceTypeById(@PathVariable Long id) {
        return serviceTypeService.getServiceTypeById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ServiceTypeDTO>> getAllServiceTypes() {
        List<ServiceTypeDTO> serviceTypeDTOs = serviceTypeService.getAllServiceTypes().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(serviceTypeDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceTypeDTO> updateServiceType(@PathVariable Long id, @RequestBody ServiceTypeDTO serviceTypeDTO) {
        ServiceType serviceTypeToUpdate = convertToEntity(serviceTypeDTO);
        serviceTypeToUpdate.setId(id); // Ensure the ID is set for the update
        ServiceType updatedServiceType = serviceTypeService.updateServiceType(serviceTypeToUpdate);
        ServiceTypeDTO updatedServiceTypeDTO = convertToDTO(updatedServiceType);
        return ResponseEntity.ok().body(updatedServiceTypeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceType(@PathVariable Long id) {
        serviceTypeService.deleteServiceType(id);
        return ResponseEntity.ok().build();
    }

    // Utility method to convert DTO to entity
    private ServiceType convertToEntity(ServiceTypeDTO dto) {
        ServiceType serviceType = new ServiceType();
        serviceType.setName(dto.getName());
        serviceType.setDescription(dto.getDescription());
        serviceType.setRate(dto.getRate());
        return serviceType;
    }

    // Utility method to convert entity to DTO
    private ServiceTypeDTO convertToDTO(ServiceType serviceType) {
        ServiceTypeDTO dto = new ServiceTypeDTO();
        dto.setId(serviceType.getId());
        dto.setName(serviceType.getName());
        dto.setDescription(serviceType.getDescription());
        dto.setRate(serviceType.getRate());
        return dto;
    }
}
