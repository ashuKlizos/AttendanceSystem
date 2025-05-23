package com.klizo.attendance.userservice.dto;

import com.klizo.attendance.userservice.enumeration.EmployeeStatus;
import com.klizo.attendance.userservice.enumeration.EmployeeType;
import lombok.Data;

@Data
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String designation;
    private EmployeeStatus status;
    private EmployeeType employeeType;
}