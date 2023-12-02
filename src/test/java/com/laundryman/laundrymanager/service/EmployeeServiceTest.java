package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.Employee;
import com.laundryman.laundrymanager.repository.EmployeeRepository;
import com.laundryman.laundrymanager.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Alice");
        employee.setLastName("Smith");
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);
        assertNotNull(savedEmployee);
        assertEquals("Alice", savedEmployee.getFirstName());
        assertEquals("Smith", savedEmployee.getLastName());
    }

    @Test
    void getEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1L);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);
        assertTrue(foundEmployee.isPresent());
        assertEquals(1L, foundEmployee.get().getId());
    }

    @Test
    void getAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(new Employee(), new Employee()));

        assertEquals(2, employeeService.getAllEmployees().size());
    }

    @Test
    void updateEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Updated");
        employee.setLastName("Name");
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee updatedEmployee = employeeService.updateEmployee(employee);
        assertNotNull(updatedEmployee);
        assertEquals("Updated", updatedEmployee.getFirstName());
        assertEquals("Name", updatedEmployee.getLastName());
    }

    @Test
    void deleteEmployee() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }
}
