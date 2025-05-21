package com.klizo.attendance.attendanceservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "project_assignment")
public class ProjectAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String firstName;
    private String lastName;

    private String projectName;
    private String projectManager;

    private LocalDate assignedFrom;
    private LocalDate assignedTo;

    private Boolean isActive;
}
