package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
    List<ProjectAssignment> findByEmployeeId(Long employeeId);
}
