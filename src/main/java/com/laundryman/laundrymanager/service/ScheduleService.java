package com.laundryman.laundrymanager.service;

import com.laundryman.laundrymanager.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);
    Optional<Schedule> getScheduleById(Long id);
    List<Schedule> getAllSchedules();
    Schedule updateSchedule(Schedule schedule);
    void deleteSchedule(Long id);
}
