package com.klizo.attendance.attendanceservice.dto;

import com.klizo.attendance.attendanceservice.enumeration.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EarlyLeaveResponseDto {
    private Long id;
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate date;
    private LocalTime leaveTime;
    private String reason;
    private RequestStatus status;
}