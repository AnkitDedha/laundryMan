package com.laundryman.laundrymanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundryman.laundrymanager.dto.ScheduleDTO;
import com.laundryman.laundrymanager.model.Schedule;
import com.laundryman.laundrymanager.service.ScheduleService;
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
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Set up necessary mock behavior here
    }

    @Test
    void createSchedule() throws Exception {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(1L); // Set additional fields as required

        mockMvc.perform(post("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scheduleDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getScheduleById() throws Exception {
        Schedule mockSchedule = new Schedule();
        mockSchedule.setId(1L); // Set other required fields of mockSchedule
        when(scheduleService.getScheduleById(1L)).thenReturn(Optional.of(mockSchedule));

        ScheduleDTO scheduleDTO = convertToScheduleDTO(mockSchedule); // Assume this conversion logic exists
        mockMvc.perform(get("/api/schedules/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(scheduleDTO.getId()));
    }

    @Test
    void getAllSchedules() throws Exception {
        when(scheduleService.getAllSchedules()).thenReturn(Arrays.asList(new Schedule(), new Schedule())); // Assuming conversion in service

        mockMvc.perform(get("/api/schedules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    // Utility method to convert Schedule to ScheduleDTO
    private ScheduleDTO convertToScheduleDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        // Map other fields from Schedule to ScheduleDTO as per your class structure
        return dto;
    }
}
