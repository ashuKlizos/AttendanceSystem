package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.ClockInOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClockInOutRepository extends JpaRepository<ClockInOut, Long> {
}
