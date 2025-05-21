package com.klizo.attendance.notificationservice.entity;


import com.klizo.attendance.notificationservice.enumeration.NotificationStatus;
import com.klizo.attendance.notificationservice.enumeration.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "notification_logs")
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String recipient;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private String eventType;

    private int retries;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    private LocalDateTime timestamp;

    private Long templateId;

    private Long slackConfigId;
}

