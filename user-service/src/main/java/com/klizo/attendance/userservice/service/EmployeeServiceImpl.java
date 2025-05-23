package com.klizo.attendance.userservice.service;

import com.klizo.attendance.userservice.dto.AuthenticationResponse;
import com.klizo.attendance.userservice.dto.LoginRequest;
import com.klizo.attendance.userservice.entity.Employee;
import com.klizo.attendance.userservice.entity.EmployeeDetails;
import com.klizo.attendance.userservice.enumeration.EmployeeStatus;
import com.klizo.attendance.userservice.enumeration.EmployeeType;
import com.klizo.attendance.userservice.enumeration.Role;
import com.klizo.attendance.userservice.enumeration.ShiftTiming;
import com.klizo.attendance.userservice.exception.EmployeeDetailsAlreadyExistsException;
import com.klizo.attendance.userservice.exception.InvalidCredentialsException;
import com.klizo.attendance.userservice.exception.InvalidTokenException;
import com.klizo.attendance.userservice.repository.EmployeeDetailsRepository;
import com.klizo.attendance.userservice.repository.EmployeeRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeDetailsRepository employeeDetailsRepository,
                               PasswordEncoder passwordEncoder,
                               JwtService jwtService) {
        this.employeeRepository = employeeRepository;
        this.employeeDetailsRepository = employeeDetailsRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String createEmployee(Employee employee) {
        try
        {
            if (employeeRepository.findByEmail(employee.getEmail()) != null) {
                throw new EmployeeDetailsAlreadyExistsException("Employee with this email already exists");
            }

            if (employee.getEmployeeDetails() == null) {
                throw new IllegalArgumentException("Employee details are required");
            }

            employee.getEmployeeDetails().setEmployee(employee);

            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employee.setJoiningDate(employee.getJoiningDate() != null ? employee.getJoiningDate() : LocalDate.now());
            employee.setDesignation(employee.getDesignation() != null ? employee.getDesignation() : "FresherDeveloper");
            employee.setRole(employee.getRole() != null ? employee.getRole() : Role.EMPLOYEE);
            employee.setEmployeeType(employee.getEmployeeType() != null ? employee.getEmployeeType() : EmployeeType.FULL_TIME);

            if (employee.getShiftTiming() == null) {
                employee.setShiftTiming(ShiftTiming.AFTERNOON);
                employee.setShiftStartTime(LocalTime.of(12, 0));
                employee.setShiftEndTime(LocalTime.of(21, 0));
            }

            employee.setCreatedAt(LocalDateTime.now());
            employee.setUpdatedAt(LocalDateTime.now());
            employee.setStatus(EmployeeStatus.ACTIVE);
        }
        catch (Exception e)
        {
            throw new InvalidCredentialsException("Invalid Employee Details: " + e);
        }

        try {
            employeeRepository.save(employee);
            return "Employee Created Successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error creating employee: " + e.getMessage());
        }
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        Employee employee = employeeRepository.findByEmail(loginRequest.getEmail());
        if (employee == null) {
            throw new InvalidTokenException("Invalid email or password");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), employee.getPassword())) {
            throw new InvalidTokenException("Invalid email or password");
        }

        String token = jwtService.generateToken(employee);
        return new AuthenticationResponse(token);
    }

    @Override
    public String deleteEmployee(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        EmployeeDetails employeeDetails = employeeDetailsRepository.findById(employee.getEmployeeDetails().getId()).get();
        log.info("Deleting Employee: {} {} EmployeeId: {}" , employeeDetails.getFirstName() , employeeDetails.getLastName() , employeeId);
        employeeRepository.deleteById(employeeId);
        employeeDetailsRepository.deleteById(employeeDetails.getId());
        return "Employee Deleted Successfully";
    }
}