package com.klizo.attendance.attendanceservice.dto;

import com.klizo.attendance.attendanceservice.enumeration.LeaveType;
import com.klizo.attendance.attendanceservice.enumeration.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveResponseDto {
    private Long id;
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LeaveType type;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
    private RequestStatus status;
}