package com.klizo.attendance.notificationservice.exception;

public class InvalidNotificationRequestException extends RuntimeException {
  public InvalidNotificationRequestException(String message) {
    super(message);
  }
}
