package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}
