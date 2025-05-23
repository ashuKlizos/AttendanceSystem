package com.klizo.attendance.attendanceservice.service;

import com.klizo.attendance.attendanceservice.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AttendanceService {

    String recordClockIn(String userId);

    String recordClockOut(String userId);

    // New methods with DTOs
    ApiResponseDto<ClockInOutResponseDto> clockIn(String token, ClockInRequestDto request);
    ApiResponseDto<ClockInOutResponseDto> clockOut(String token, ClockOutRequestDto request);
    
    // Leave requests
    ApiResponseDto<LeaveResponseDto> submitLeaveRequest(String token, LeaveRequestDto request);
    ApiResponseDto<List<LeaveResponseDto>> getLeaveRequests(String token);
    ApiResponseDto<LeaveResponseDto> updateLeaveRequest(String token, Long requestId, LeaveRequestDto request);
    
    // Shift change
    ApiResponseDto<ShiftChangeResponseDto> requestShiftChange(String token, ShiftChangeRequestDto request);
    ApiResponseDto<List<ShiftChangeResponseDto>> getShiftChangeRequests(String token);
    
    // Holidays
    ApiResponseDto<HolidayResponseDto> addHoliday(String token, HolidayRequestDto request);
    ApiResponseDto<List<HolidayResponseDto>> getAllHolidays();
    
    // Early leave
    ApiResponseDto<EarlyLeaveResponseDto> requestEarlyLeave(String token, EarlyLeaveRequestDto request);
    ApiResponseDto<List<EarlyLeaveResponseDto>> getEarlyLeaveRequests(String token);
    
    // Project assignment
    ApiResponseDto<ProjectAssignmentResponseDto> assignProject(String token, ProjectAssignmentRequestDto request);
    ApiResponseDto<List<ProjectAssignmentResponseDto>> getProjectAssignments(String token, Long employeeId);
    
    // Daily summary
    ApiResponseDto<DailySummaryDto> getDailySummary(String token, LocalDate date);
}
