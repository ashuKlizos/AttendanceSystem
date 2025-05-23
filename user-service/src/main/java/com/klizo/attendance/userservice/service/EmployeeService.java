package com.klizo.attendance.userservice.service;

import com.klizo.attendance.userservice.dto.AuthenticationResponse;
import com.klizo.attendance.userservice.dto.LoginRequest;
import com.klizo.attendance.userservice.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    String createEmployee(Employee employee);
    AuthenticationResponse login(LoginRequest loginRequest);
}