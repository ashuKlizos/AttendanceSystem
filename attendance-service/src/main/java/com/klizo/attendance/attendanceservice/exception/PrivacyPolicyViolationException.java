package com.klizo.attendance.attendanceservice.exception;

public class PrivacyPolicyViolationException extends RuntimeException {
    public PrivacyPolicyViolationException(String message) {
        super(message);
    }
}
