package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Optional<Employee> getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long id);
}
