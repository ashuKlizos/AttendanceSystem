package com.klizo.attendance.attendanceservice.entity;

import com.klizo.attendance.attendanceservice.enumeration.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "early_leave_request")
public class EarlyLeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String firstName;
    private String lastName;

    private LocalDate date;
    private LocalTime leaveTime;

    private String reason;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
