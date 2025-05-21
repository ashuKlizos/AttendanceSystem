package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
}
