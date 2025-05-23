package com.klizo.attendance.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClockOutRequestDto {
    private String location; // Optional: for location tracking
    private String deviceInfo; // Optional: for device tracking
}