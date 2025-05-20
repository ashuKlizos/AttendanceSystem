package com.klizo.attendance.userservice.repository;

import com.klizo.attendance.userservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
