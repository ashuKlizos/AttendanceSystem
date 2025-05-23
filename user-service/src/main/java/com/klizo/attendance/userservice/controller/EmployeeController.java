package com.klizo.attendance.userservice.controller;

import com.klizo.attendance.userservice.dto.AuthenticationResponse;
import com.klizo.attendance.userservice.dto.LoginRequest;
import com.klizo.attendance.userservice.entity.Employee;
import com.klizo.attendance.userservice.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'DIRECTOR')")
    @PostMapping("/createEmployee")
    public String createEmployee(@RequestBody Employee employee) {
        log.info("Creating Employee: " + employee);
        return employeeService.createEmployee(employee);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> employeeLogin(@RequestBody LoginRequest loginRequest) {
        return  ResponseEntity.ok(employeeService.login(loginRequest));
    }

    @PreAuthorize("hasAnyRole('MANAGER' , 'DIRECTOR' , 'HR')")
    @PostMapping("/delete-employee/{employeeId}")
    public String deleteEmployee(@PathVariable long employeeId) {
        log.info("Deleting Employee: " + employeeId);
        return employeeService.deleteEmployee(employeeId);
    }

}