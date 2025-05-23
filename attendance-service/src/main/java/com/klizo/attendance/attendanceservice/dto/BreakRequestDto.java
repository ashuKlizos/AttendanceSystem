package com.klizo.attendance.attendanceservice.dto;


import com.klizo.attendance.attendanceservice.enumeration.ClockType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BreakRequestDto {
    private ClockType type;
}