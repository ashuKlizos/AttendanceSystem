package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
}
