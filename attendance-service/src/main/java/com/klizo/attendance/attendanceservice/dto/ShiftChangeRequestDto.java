package com.klizo.attendance.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftChangeRequestDto {
    private LocalDate requestedDate;
    private String fromShift;
    private String toShift;
    private String reason;
}