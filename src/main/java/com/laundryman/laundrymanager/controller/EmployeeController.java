package com.laundryman.laundrymanager.controller;

import com.laundryman.laundrymanager.dto.EmployeeDTO;
import com.laundryman.laundrymanager.model.Employee;
import com.laundryman.laundrymanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        EmployeeDTO savedEmployeeDTO = convertToDTO(savedEmployee);
        return ResponseEntity.ok().body(savedEmployeeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployees().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(employeeDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee employeeToUpdate = convertToEntity(employeeDTO);
        employeeToUpdate.setId(id); // Ensure the ID is set for the update
        Employee updatedEmployee = employeeService.updateEmployee(employeeToUpdate);
        EmployeeDTO updatedEmployeeDTO = convertToDTO(updatedEmployee);
        return ResponseEntity.ok().body(updatedEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    // Utility method to convert DTO to entity
    private Employee convertToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        // Map fields from EmployeeDTO to Employee
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        // Add other field mappings as necessary
        return employee;
    }

    // Utility method to convert entity to DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        // Map fields from Employee to EmployeeDTO
        dto.setId(employee.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        // Add other field mappings as necessary
        return dto;
    }
}
