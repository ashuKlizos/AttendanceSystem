package com.klizo.attendance.userservice.repository;

import com.klizo.attendance.userservice.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {
}
