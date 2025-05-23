package com.klizo.attendance.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailySummaryDto {
    private Long id;
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate date;
    private Long totalWorkedMinutes;
    private Long totalBreakMinutes;
    private Boolean exceededBreakLimit;
}