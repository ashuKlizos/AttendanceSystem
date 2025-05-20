package com.klizo.attendance.userservice.entity;

import com.klizo.attendance.userservice.enumeration.EmployeeStatus;
import com.klizo.attendance.userservice.enumeration.EmployeeType;
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
@Table(name = "employee_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private Employee employee;

    @NotNull
    private LocalDate joiningDate;

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