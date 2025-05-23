package com.klizo.attendance.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailySummaryDto {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate date;
    private LocalDateTime clockInTime;
    private LocalDateTime clockOutTime;
    private Long totalWorkedMinutes;
    private Long totalBreakMinutes;
    private Boolean exceededBreakLimit;
    private String status; // PRESENT, ABSENT, HALF_DAY, ON_LEAVE, HOLIDAY
}