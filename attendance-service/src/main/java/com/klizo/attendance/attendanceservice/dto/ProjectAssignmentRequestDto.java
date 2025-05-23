package com.klizo.attendance.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAssignmentRequestDto {
    private String projectName;
    private String projectManager;
    private LocalDate assignedFrom;
    private LocalDate assignedTo;
}