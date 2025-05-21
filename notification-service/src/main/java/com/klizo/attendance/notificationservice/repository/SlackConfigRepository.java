package com.klizo.attendance.notificationservice.repository;

import com.klizo.attendance.notificationservice.entity.SlackConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlackConfigRepository extends JpaRepository<SlackConfig, Long> {
}
