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
@Table(name = "members_on_leave")
public class MembersOnLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private String firstName;

    private String lastName;

    private LocalDate leaveDate;

    private String leaveType;

    private String reason;
}