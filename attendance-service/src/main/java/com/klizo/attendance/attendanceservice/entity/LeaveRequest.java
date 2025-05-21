package com.klizo.attendance.attendanceservice.entity;

import com.klizo.attendance.attendanceservice.enumeration.LeaveType;
import com.klizo.attendance.attendanceservice.enumeration.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "leave_request")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private LeaveType type;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String reason;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
