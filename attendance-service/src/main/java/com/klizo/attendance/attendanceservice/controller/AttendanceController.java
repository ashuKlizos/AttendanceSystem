package com.klizo.attendance.attendanceservice.controller;

import com.klizo.attendance.attendanceservice.dto.*;
import com.klizo.attendance.attendanceservice.enumeration.ClockType;
import com.klizo.attendance.attendanceservice.service.AttendanceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }


//    @PreAuthorize("hasAnyRole('MANAGER' , 'EMPLOYEE' , 'DIRECTOR' , 'TRAINEE' , 'HR' , 'PROJECT_MANAGER')")
//    @PostMapping("/clock-in")
//    public String clockIn(@RequestHeader("Authorization") String token , ClockType type) {
//        return attendanceService.recordClockIn(token , type);
//    }
//
//    @PreAuthorize("hasAnyRole('MANAGER' , 'EMPLOYEE' , 'DIRECTOR' , 'TRAINEE' , 'HR' , 'PROJECT_MANAGER')")
//    @PostMapping("/clock-out")
//    public String clockOut(@RequestHeader("Authorization") String token , ClockType type) {
//        return attendanceService.recordClockOut(token , type);
//    }

    @PostMapping("/clock-in")
    public ApiResponseDto<ClockInOutResponseDto> clockInV1(
            @RequestHeader("Authorization") String token,
            @RequestBody ClockInRequestDto request) {
        return attendanceService.clockIn(token, request);
    }

    @PostMapping("/clock-out")
    public ApiResponseDto<ClockInOutResponseDto> clockOutV1(
            @RequestHeader("Authorization") String token,
            @RequestBody ClockOutRequestDto request) {
        return attendanceService.clockOut(token, request);
    }

    @PostMapping("/break")
    public ApiResponseDto<ClockInOutResponseDto> Break(
            @RequestHeader("Authorization") String token,
            @RequestBody BreakRequestDto request) {
        return attendanceService.Break(token, request);
    }

    @PostMapping("/leave-request")
    public ApiResponseDto<LeaveResponseDto> submitLeaveRequest(
            @RequestHeader("Authorization") String token,
            @RequestBody LeaveRequestDto request) {
        return attendanceService.submitLeaveRequest(token, request);
    }
    
    @GetMapping("/leave-requests")
    public ApiResponseDto<List<LeaveResponseDto>> getLeaveRequests(
            @RequestHeader("Authorization") String token) {
        return attendanceService.getLeaveRequests(token);
    }
    
    @PutMapping("/leave-request/{requestId}")
    public ApiResponseDto<LeaveResponseDto> updateLeaveRequest(
            @RequestHeader("Authorization") String token,
            @PathVariable Long requestId,
            @RequestBody LeaveRequestDto request) {
        return attendanceService.updateLeaveRequest(token, requestId, request);
    }

    @PostMapping("/shift-change")
    public ApiResponseDto<ShiftChangeResponseDto> requestShiftChange(
            @RequestHeader("Authorization") String token,
            @RequestBody ShiftChangeRequestDto request) {
        return attendanceService.requestShiftChange(token, request);
    }
    
    @GetMapping("/shift-changes")
    public ApiResponseDto<List<ShiftChangeResponseDto>> getShiftChangeRequests(
            @RequestHeader("Authorization") String token) {
        return attendanceService.getShiftChangeRequests(token);
    }

    @PostMapping("/holiday")
    public ApiResponseDto<HolidayResponseDto> addHoliday(
            @RequestHeader("Authorization") String token,
            @RequestBody HolidayRequestDto request) {
        return attendanceService.addHoliday(token, request);
    }
    
    @GetMapping("/holidays")
    public ApiResponseDto<List<HolidayResponseDto>> getAllHolidays() {
        return attendanceService.getAllHolidays();
    }

    @PostMapping("/early-leave")
    public ApiResponseDto<EarlyLeaveResponseDto> requestEarlyLeave(
            @RequestHeader("Authorization") String token,
            @RequestBody EarlyLeaveRequestDto request) {
        return attendanceService.requestEarlyLeave(token, request);
    }
    
    @GetMapping("/early-leaves")
    public ApiResponseDto<List<EarlyLeaveResponseDto>> getEarlyLeaveRequests(
            @RequestHeader("Authorization") String token) {
        return attendanceService.getEarlyLeaveRequests(token);
    }

    @PostMapping("/project-assignment")
    public ApiResponseDto<ProjectAssignmentResponseDto> assignProject(
            @RequestHeader("Authorization") String token,
            @RequestBody ProjectAssignmentRequestDto request) {
        return attendanceService.assignProject(token, request);
    }
    
    @GetMapping("/project-assignments")
    public ApiResponseDto<List<ProjectAssignmentResponseDto>> getProjectAssignments(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) Long employeeId) {
        return attendanceService.getProjectAssignments(token, employeeId);
    }

    @GetMapping("/daily-summary")
    public ApiResponseDto<DailySummaryDto> getDailySummary(
            @RequestHeader("Authorization") String token,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return attendanceService.getDailySummary(token, date);
    }
}