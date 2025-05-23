package com.klizo.attendance.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClockInOutResponseDto {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LocalDateTime timestamp;
    private String type;
}