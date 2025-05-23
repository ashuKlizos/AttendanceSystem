package com.klizo.attendance.attendanceservice.dto;

import com.klizo.attendance.attendanceservice.enumeration.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftChangeResponseDto {
    private Long id;
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate requestedDate;
    private String fromShift;
    private String toShift;
    private String reason;
    private RequestStatus status;
}