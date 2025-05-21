package com.klizo.attendance.attendanceservice.entity;

import com.klizo.attendance.attendanceservice.enumeration.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;             // From token
    private String firstName;
    private String lastName;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType type;      // IN_APP, EMAIL, etc.

    private Boolean isRead;

    private LocalDateTime sentAt;
}
