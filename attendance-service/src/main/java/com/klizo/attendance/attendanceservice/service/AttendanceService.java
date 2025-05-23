package com.klizo.attendance.attendanceservice.service;

import com.klizo.attendance.attendanceservice.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AttendanceService {

//    String recordClockIn(String token , ClockType type);
//
//    String recordClockOut(String userId , ClockType type);

    ApiResponseDto<ClockInOutResponseDto> clockIn(String token, ClockInRequestDto request);

    ApiResponseDto<ClockInOutResponseDto> clockOut(String token, ClockOutRequestDto request);

    ApiResponseDto<ClockInOutResponseDto> Break(String token, BreakRequestDto request);

    ApiResponseDto<LeaveResponseDto> submitLeaveRequest(String token, LeaveRequestDto request);

    ApiResponseDto<List<LeaveResponseDto>> getLeaveRequests(String token);

    ApiResponseDto<LeaveResponseDto> updateLeaveRequest(String token, Long requestId, LeaveRequestDto request);

    ApiResponseDto<ShiftChangeResponseDto> requestShiftChange(String token, ShiftChangeRequestDto request);

    ApiResponseDto<List<ShiftChangeResponseDto>> getShiftChangeRequests(String token);

    ApiResponseDto<HolidayResponseDto> addHoliday(String token, HolidayRequestDto request);

    ApiResponseDto<List<HolidayResponseDto>> getAllHolidays();

    ApiResponseDto<EarlyLeaveResponseDto> requestEarlyLeave(String token, EarlyLeaveRequestDto request);

    ApiResponseDto<List<EarlyLeaveResponseDto>> getEarlyLeaveRequests(String token);

    ApiResponseDto<ProjectAssignmentResponseDto> assignProject(String token, ProjectAssignmentRequestDto request);

    ApiResponseDto<List<ProjectAssignmentResponseDto>> getProjectAssignments(String token, Long employeeId);

    ApiResponseDto<DailySummaryDto> getDailySummary(String token, LocalDate date);
}
