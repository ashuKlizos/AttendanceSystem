package com.klizo.attendance.userservice.repository;

import com.klizo.attendance.userservice.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);

    Optional<Employee> findById(Long id);
}
