package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.MembersOnLeave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersOnLeaveRepository extends JpaRepository<MembersOnLeave, Long> {
}
