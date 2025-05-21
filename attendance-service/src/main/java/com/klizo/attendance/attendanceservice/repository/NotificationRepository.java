package com.klizo.attendance.attendanceservice.repository;

import com.klizo.attendance.attendanceservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
