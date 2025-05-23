package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.ClockInOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ClockInOutRepository extends JpaRepository<ClockInOut, Long> {

    List<ClockInOut> findByEmployeeIdAndDate(Long employeeId, LocalDate date);

}
