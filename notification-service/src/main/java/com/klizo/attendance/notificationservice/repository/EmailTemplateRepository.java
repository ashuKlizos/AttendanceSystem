package com.klizo.attendance.notificationservice.repository;

import com.klizo.attendance.notificationservice.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
}
