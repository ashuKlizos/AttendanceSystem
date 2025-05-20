package com.klizo.attendance.userservice.exception;

public class EmployeeDetailsAlreadyExistsException extends RuntimeException {
  public EmployeeDetailsAlreadyExistsException(String message) {
    super(message);
  }
}
