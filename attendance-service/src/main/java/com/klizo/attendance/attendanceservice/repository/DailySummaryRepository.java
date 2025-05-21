package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.DailySummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailySummaryRepository extends JpaRepository<DailySummary, Long> {
}
