package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.EarlyLeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EarlyLeaveRequestRepository extends JpaRepository<EarlyLeaveRequest, Long> {
    List<EarlyLeaveRequest> findByEmployeeId(Long employeeId);
}
