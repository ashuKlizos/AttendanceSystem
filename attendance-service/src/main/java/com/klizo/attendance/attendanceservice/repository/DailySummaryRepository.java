package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.DailySummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DailySummaryRepository extends JpaRepository<DailySummary, Long> {
    DailySummary findByEmployeeIdAndDate(Long employeeId, LocalDate date);
}
