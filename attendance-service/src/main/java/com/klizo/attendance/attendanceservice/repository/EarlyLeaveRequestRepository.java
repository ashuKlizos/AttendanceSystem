package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.EarlyLeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarlyLeaveRequestRepository extends JpaRepository<EarlyLeaveRequest, Long> {
}
