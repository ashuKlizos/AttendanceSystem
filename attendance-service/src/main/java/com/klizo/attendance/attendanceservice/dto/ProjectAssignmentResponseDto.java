package com.klizo.attendance.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAssignmentResponseDto {
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