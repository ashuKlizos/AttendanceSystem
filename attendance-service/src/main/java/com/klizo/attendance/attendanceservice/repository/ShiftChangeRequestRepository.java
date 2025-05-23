package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.ShiftChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftChangeRequestRepository extends JpaRepository<ShiftChangeRequest, Long> {
    List<ShiftChangeRequest> findByEmployeeId(Long employeeId);
}
