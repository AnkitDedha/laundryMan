package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.ServiceType;
import com.laundryman.laundrymanager.repository.ServiceTypeRepository;
import com.laundryman.laundrymanager.service.impl.ServiceTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTypeServiceTest {

    @Mock
    private ServiceTypeRepository serviceTypeRepository;

    @InjectMocks
    private ServiceTypeServiceImpl serviceTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveServiceType() {
        ServiceType serviceType = new ServiceType();
        serviceType.setName("Dry Cleaning"); // Assuming there's a name field
        when(serviceTypeRepository.save(serviceType)).thenReturn(serviceType);

        ServiceType savedServiceType = serviceTypeService.saveServiceType(serviceType);
        assertNotNull(savedServiceType);
        assertEquals("Dry Cleaning", savedServiceType.getName());
    }

    @Test
    void getServiceTypeById() {
        ServiceType serviceType = new ServiceType();
        serviceType.setId(1L);
        when(serviceTypeRepository.findById(1L)).thenReturn(Optional.of(serviceType));

        Optional<ServiceType> foundServiceType = serviceTypeService.getServiceTypeById(1L);
        assertTrue(foundServiceType.isPresent());
        assertEquals(1L, foundServiceType.get().getId());
    }

    @Test
    void getAllServiceTypes() {
        when(serviceTypeRepository.findAll()).thenReturn(Arrays.asList(new ServiceType(), new ServiceType()));

        List<ServiceType> serviceTypes = serviceTypeService.getAllServiceTypes();
        assertEquals(2, serviceTypes.size());
    }

    @Test
    void updateServiceType() {
        ServiceType serviceType = new ServiceType();
        serviceType.setId(1L);
        serviceType.setName("Updated Service");
        when(serviceTypeRepository.save(serviceType)).thenReturn(serviceType);

        ServiceType updatedServiceType = serviceTypeService.updateServiceType(serviceType);
        assertNotNull(updatedServiceType);
        assertEquals("Updated Service", updatedServiceType.getName());
    }

    @Test
    void deleteServiceType() {
        doNothing().when(serviceTypeRepository).deleteById(1L);

        serviceTypeService.deleteServiceType(1L);
        verify(serviceTypeRepository, times(1)).deleteById(1L);
    }
}
