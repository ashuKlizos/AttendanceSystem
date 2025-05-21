package com.klizo.attendance.attendanceservice.entity;

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
@Table(name = "shift_change_request")
public class ShiftChangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private String firstName;

    private String lastName;

    private LocalDate requestedDate;

    private String fromShift;

    private String toShift;

    private String reason;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}