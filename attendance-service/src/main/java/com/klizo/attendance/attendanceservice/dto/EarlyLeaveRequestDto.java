package com.klizo.attendance.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EarlyLeaveRequestDto {
    private LocalDate date;
    private LocalTime leaveTime;
    private String reason;
}