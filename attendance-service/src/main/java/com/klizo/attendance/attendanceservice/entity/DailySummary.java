package com.klizo.attendance.attendanceservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "daily_summary", uniqueConstraints = @UniqueConstraint(columnNames = {"employeeId", "date"}))
public class DailySummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String firstName;
    private String lastName;

    private LocalDate date;

    private Long totalWorkedMinutes;
    private Long totalBreakMinutes;

    private Boolean exceededBreakLimit;
}
