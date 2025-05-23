package com.klizo.attendance.attendanceservice.dto;

import com.klizo.attendance.attendanceservice.enumeration.ClockType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClockOutRequestDto {
    private ClockType type;
}