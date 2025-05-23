package com.klizo.attendance.attendanceservice.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AttendanceServiceImpl {

    public String recordClockIn(String userId) {
        // Save to database
        return "Clock-in recorded for user " + userId + " at " + LocalDateTime.now();
    }

    public String recordClockOut(String userId) {
        // Save to database
        return "Clock-out recorded for user " + userId + " at " + LocalDateTime.now();
    }

}
