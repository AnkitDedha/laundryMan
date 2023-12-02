package com.laundryman.laundrymanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundryman.laundrymanager.dto.EmployeeDTO;
import com.laundryman.laundrymanager.model.Employee;
import com.laundryman.laundrymanager.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Setup necessary mock behavior here
    }

    @Test
    void createEmployee() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Jane");
        employeeDTO.setLastName("Doe");

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void getEmployeeById() throws Exception {
        Employee mockEmployee = new Employee();
        mockEmployee.setId(1L);
        // Set other required fields of mockEmployee as per your Employee class
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(mockEmployee));

        EmployeeDTO employeeDTO = convertToEmployeeDTO(mockEmployee); // Conversion logic
        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employeeDTO.getId()));
    }

    @Test
    void getAllEmployees() throws Exception {
        List<Employee> mockEmployees = Arrays.asList(new Employee(), new Employee());
        // Set required fields of Employee objects in mockEmployees list
        when(employeeService.getAllEmployees()).thenReturn(mockEmployees);

        List<EmployeeDTO> employeeDTOs = mockEmployees.stream()
                .map(this::convertToEmployeeDTO)
                .collect(Collectors.toList());
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(employeeDTOs.size()));
    }

    @Test
    void updateEmployee() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setFirstName("Updated Name");

        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Updated Name"));
    }

    @Test
    void deleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isOk());
    }


    // Utility method to convert Employee to EmployeeDTO
    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        // Map other fields from Employee to EmployeeDTO as per your class structure
        return dto;
    }
}
