package com.klizo.attendance.notificationservice.exception;

public class NotificationSendFailureException extends RuntimeException {
    public NotificationSendFailureException(String message) {
        super(message);
    }
}
