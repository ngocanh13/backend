package com.example.api_sem4.repository;

import com.example.api_sem4.entity.Schedule;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule>findByClazzIdAndDayOfWeek(Long classId, String dayOfWeek);
    @Transactional
    @Modifying
    void deleteByClazzId(Long classId);
}
