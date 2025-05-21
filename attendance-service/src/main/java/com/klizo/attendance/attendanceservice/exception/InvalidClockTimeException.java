package com.klizo.attendance.attendanceservice.exception;

public class InvalidClockTimeException extends RuntimeException {
    public InvalidClockTimeException(String message) {
        super(message);
    }
}
