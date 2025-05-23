package com.klizo.attendance.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

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

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @Past
    private Date birthDate;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String personalEmail;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(length = 500)
    private String address;

    private String bloodGroup;

    private String gender;

    @OneToOne(mappedBy = "employeeDetails")
    private Employee employee;

}
