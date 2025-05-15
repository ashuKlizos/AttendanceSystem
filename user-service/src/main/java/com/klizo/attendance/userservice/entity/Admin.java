package com.klizo.attendance.userservice.entity;
import com.klizo.attendance.userservice.enumeration.AdminRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;

    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private AdminRole role; // Enum like SUPER_ADMIN, MODERATOR, etc.

    private Boolean isActive;
    private Boolean isDeleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Audit trail if needed
    private String createdBy;

    private String updatedBy;


}
