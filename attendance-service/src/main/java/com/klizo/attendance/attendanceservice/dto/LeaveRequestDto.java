package com.klizo.attendance.attendanceservice.dto;

import com.klizo.attendance.attendanceservice.enumeration.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDto {
    private LeaveType type;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
}