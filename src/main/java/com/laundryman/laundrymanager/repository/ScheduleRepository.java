package com.laundryman.laundrymanager.repository;

import com.laundryman.laundrymanager.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // Additional custom methods can be defined here
}
