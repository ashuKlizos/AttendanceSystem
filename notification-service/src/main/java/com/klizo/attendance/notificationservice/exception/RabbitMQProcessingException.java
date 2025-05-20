package com.klizo.attendance.notificationservice.exception;

public class RabbitMQProcessingException extends RuntimeException {
  public RabbitMQProcessingException(String message) {
    super(message);
  }
}
