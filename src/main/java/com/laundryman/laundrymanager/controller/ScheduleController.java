package com.laundryman.laundrymanager.controller;

import com.laundryman.laundrymanager.dto.ScheduleDTO;
import com.laundryman.laundrymanager.model.Schedule;
import com.laundryman.laundrymanager.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertToEntity(scheduleDTO);
        Schedule savedSchedule = scheduleService.saveSchedule(schedule);
        ScheduleDTO savedScheduleDTO = convertToDTO(savedSchedule);
        return ResponseEntity.ok().body(savedScheduleDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<ScheduleDTO> scheduleDTOs = scheduleService.getAllSchedules().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(scheduleDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) {
        Schedule scheduleToUpdate = convertToEntity(scheduleDTO);
        scheduleToUpdate.setId(id); // Ensure the ID is set for the update
        Schedule updatedSchedule = scheduleService.updateSchedule(scheduleToUpdate);
        ScheduleDTO updatedScheduleDTO = convertToDTO(updatedSchedule);
        return ResponseEntity.ok().body(updatedScheduleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }

    // Utility method to convert DTO to entity
    private Schedule convertToEntity(ScheduleDTO dto) {
        Schedule schedule = new Schedule();
        schedule.setScheduledTime(dto.getScheduledTime());
        schedule.setType(dto.getType());
        // Convert and set the associated order to entity if necessary
        // schedule.setOrder(convertToOrderEntity(dto.getOrder()));
        return schedule;
    }

    // Utility method to convert entity to DTO
    private ScheduleDTO convertToDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setScheduledTime(schedule.getScheduledTime());
        dto.setType(schedule.getType());
        // Convert and set the associated order to DTO if necessary
        // dto.setOrder(convertToOrderDTO(schedule.getOrder()));
        return dto;
    }
}
