package com.klizo.attendance.attendanceservice.entity;

import com.klizo.attendance.attendanceservice.enumeration.ClockType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "clock_in_out")
public class ClockInOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String firstName;
    private String lastName;

    private LocalDate date;
    private LocalDateTime inTime;
    private LocalDateTime outTime;

    @Enumerated(EnumType.STRING)
    private ClockType type;
}
