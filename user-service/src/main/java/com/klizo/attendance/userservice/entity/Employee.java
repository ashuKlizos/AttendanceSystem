package com.klizo.attendance.userservice.entity;

import com.klizo.attendance.userservice.enumeration.EmployeeStatus;
import com.klizo.attendance.userservice.enumeration.EmployeeType;
import com.klizo.attendance.userservice.enumeration.Role;
import com.klizo.attendance.userservice.enumeration.ShiftTiming;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private EmployeeDetails employeeDetails;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String password;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    private LocalDate joiningDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank
    private String designation;

    private Long reportingManagerId;

    private String workLocation;

    @Enumerated(EnumType.STRING)
    private ShiftTiming shiftTiming;

    private LocalTime shiftStartTime;

    private LocalTime shiftEndTime;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}